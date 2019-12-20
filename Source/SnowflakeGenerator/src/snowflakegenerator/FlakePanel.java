package snowflakegenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author Georgiy Farina
 * @version 19.12.2019
 */
public class FlakePanel extends javax.swing.JPanel implements MouseListener, MouseMotionListener{
    
    /**
     * Il colore dello sfondo del programma.
     */
    private Color coloreSfondoSinistro = new Color(204,255,255);
    
    /**
     * Il colore dello sfondo del programma.
     */
    private Color coloreSfondoDestro = Color.black;
    
    /**
     * Il colore del triangolo non ritagliato.
     */
    private Color coloreTriangoloOriginale = new Color(255,0,0);
    
    /**
     * Il colore dei punti.
     */
    private Color colorePunti = Color.magenta;   
    
    /**
     * Il colore della forma di ritaglio.
     */
    private Color coloreForma = Color.gray;

    /**
     * Il colore della forma di ritaglio.
     */
    private int raggio = 10;

    /**
     * Larghezza del panel del triangolo.
     */
    private int panelCenter = 512;
    
    /**
     * Altezza del panel del triangolo.
     */
    private int height = 768;
    
    /**
     * Punti della forma che creiamo.
     */
    private List<Point> punti;
    
    /**
     * Variabile che uso per salvare i punti.
     */
    private List<Point> puntiDaSalvare;
    
    /**
     * Bordo tra le estremità del panel e i 
     * punti del cateto minore del triangolo.
     */
    private int bordoOrizzontale = 100;
    
    /**
     * Lunghezza del cateto maggiore del triangolo.
     */
    private int catetoMaggiore = this.height/2;
    
    /**
     * Lunghezza del cateto minore del triangolo.
     */
    private int catetoMinore = (int)(this.catetoMaggiore/Math.sqrt(3));
    
    /**
     * Lunghezza dell'ipotenusa del triangolo.
     */
    private int ipotenusa = this.catetoMinore*2;
    
    /**
     * Coordinata orizzontale del vertice in basso a destra del triangolo.
     */
    private int centroFiocco;
    
    /**
     * Poligono della forma che creiamo.
     */
    private Polygon forma = new Polygon();
    
    /**
     * Poligono del triangolo iniziale.
     */
    private Polygon triangolo = new Polygon();
    
    /**
     * Poligono del triangolo ritagliato.
     */
    private Polygon triangoloFinale = new Polygon();
    
    /**
     * Dimensioni minime del panel.
     */
    public static final int DIM_MIN[] = {512,768};
    
    /**
     * Area del poligono della forma che creiamo.
     */
    private Area areaForma = new Area(this.forma);
    
    /**
     * Area del triangolo iniziale.
     */
    private Area areaTriangoloOriginale = new Area(this.triangolo);
    
    /**
     * Area del triangolo ritagliato.
     */
    private Area areaTriangoloRitagliato = new Area(this.triangoloFinale);
    
    /**
     * Lista contenente tutti i triangoli che compongono il fiocco finale.
     */
    private ArrayList<Shape> fioccoFinale = new ArrayList<>();
    
    /**
     * Mi dice se devo mostrare sullo schermo il triangolo originale.
     */
    private boolean disegnaTriangoloOriginale = true;
    
    /**
     * Mi dice se devo mostrare sullo schermo il triangolo ritagliato.
     */
    private boolean disegnaTriangoloFinito = true;
    
    /**
     * Mi dice se devo mostrare sullo schermo la forma che creiamo.
     */
    private boolean disegnaForma = true;
    
    /**
     * Mi dice se devo mostrare sullo schermo il fiocco generato.
     */
    private boolean disegnaFiocco = false;
    
    /**
     * Flag per capire se il fiocco è stato caricato da un csv o no.
     */
    private boolean fileCaricato = false;
    
    /**
     * Percorso dove salvare i punti della forma ritaglio
     */
    private String percorsoSalvataggioPunti = Paths.get("").toAbsolutePath().toString() + '\\' + "default.csv";
    
    /**
     * Indica se la generazione live è attiva o meno.
     */
    private boolean generazioneLive = false;
  
    /**
     * Costruttore di default
     */
    public FlakePanel() {
        initComponents();
        this.punti = new ArrayList<>();
        this.puntiDaSalvare = new ArrayList<>();
        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        triangolo.addPoint(this.bordoOrizzontale, this.height/4);
        triangolo.addPoint(this.bordoOrizzontale+this.catetoMinore, this.height/4);
        triangolo.addPoint(this.bordoOrizzontale+this.catetoMinore, this.height/4 + this.catetoMaggiore);
        
        centroFiocco = triangolo.xpoints[2];
    }
    
    /**
     * Metodo utile per aggiungere i punti della forma che creiamo.
     */
    private void addPoint(Point p){
        punti.add(p);
        puntiDaSalvare.add(p);
    }
    
    /**
     * Metodo utile per togliere i punti della forma che creiamo.
     */
    private void removeLastPoint(){
        punti.remove(punti.get(punti.size()-1));
        puntiDaSalvare.remove(puntiDaSalvare.get(puntiDaSalvare.size()-1));
    }
    
    /**
     * Metodo utile per aggiungere i punti della forma che creiamo.
     */
    private void addShape(Shape s){
        fioccoFinale.add(s);
    } 
    
    /**
     * Metodo utile per caricare i punti dal csv desiderato.
     * 
     * @param punti indica la lista di punti da utilizzare.
     */
    public void loadPunti(ArrayList<Point> punti){
        initComponents();
        this.punti = new ArrayList<>();
        this.punti = punti;
        this.forma.reset();
        
        for(int i = 0; i < this.punti.size(); i++){
            Point p = this.punti.get(i);
            System.out.println(this.punti.get(i));
            this.forma.addPoint(p.x,p.y);
        }      
        //areaForma = new Area(forma);
        this.fileCaricato = true;
        tagliaTriangoloConBottone();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bottoneIndietro = new javax.swing.JButton();
        bottoneReset = new javax.swing.JButton();
        bottoneSalva = new javax.swing.JButton();
        bottoneTaglia = new javax.swing.JButton();
        bottoneGenera = new javax.swing.JButton();
        bottonePNG = new javax.swing.JButton();
        bottoneLive = new javax.swing.JToggleButton();

        setMinimumSize(new java.awt.Dimension(512, 768));
        addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                padreRidimensionato(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                panelRidimensionato(evt);
            }
        });

        bottoneIndietro.setBackground(new java.awt.Color(255, 102, 102));
        bottoneIndietro.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        bottoneIndietro.setText("Indietro");
        bottoneIndietro.setName(""); // NOI18N
        bottoneIndietro.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                bottoneIndietroComponentHidden(evt);
            }
        });
        bottoneIndietro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneIndietrotornaIndietro(evt);
            }
        });

        bottoneReset.setBackground(new java.awt.Color(255, 102, 102));
        bottoneReset.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        bottoneReset.setText("Reset");
        bottoneReset.setName(""); // NOI18N
        bottoneReset.setPreferredSize(new java.awt.Dimension(70, 27));
        bottoneReset.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                bottoneResetComponentHidden(evt);
            }
        });
        bottoneReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneReset(evt);
            }
        });

        bottoneSalva.setBackground(new java.awt.Color(255, 102, 102));
        bottoneSalva.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        bottoneSalva.setText("Salva");
        bottoneSalva.setName(""); // NOI18N
        bottoneSalva.setPreferredSize(new java.awt.Dimension(70, 27));
        bottoneSalva.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                bottoneSalvaComponentHidden(evt);
            }
        });
        bottoneSalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneSalva(evt);
            }
        });

        bottoneTaglia.setBackground(new java.awt.Color(255, 102, 102));
        bottoneTaglia.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        bottoneTaglia.setText("Taglia");
        bottoneTaglia.setName(""); // NOI18N
        bottoneTaglia.setPreferredSize(new java.awt.Dimension(70, 27));
        bottoneTaglia.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                bottoneTagliaComponentHidden(evt);
            }
        });
        bottoneTaglia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneTaglia(evt);
            }
        });

        bottoneGenera.setBackground(new java.awt.Color(255, 102, 102));
        bottoneGenera.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        bottoneGenera.setText("Genera");
        bottoneGenera.setPreferredSize(new java.awt.Dimension(70, 27));
        bottoneGenera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneGeneraActionPerformed(evt);
            }
        });

        bottonePNG.setBackground(new java.awt.Color(255, 102, 102));
        bottonePNG.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        bottonePNG.setText("PNG");
        bottonePNG.setName(""); // NOI18N
        bottonePNG.setPreferredSize(new java.awt.Dimension(70, 27));
        bottonePNG.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                bottonePNGComponentHidden(evt);
            }
        });
        bottonePNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottonePNG(evt);
            }
        });

        bottoneLive.setText("live");
        bottoneLive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneLiveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bottoneIndietro, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bottonePNG, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(bottoneReset, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(bottoneTaglia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bottoneGenera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bottoneSalva, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(bottoneLive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(524, 524, 524))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bottoneReset, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bottoneTaglia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bottoneGenera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bottoneSalva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bottoneIndietro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottonePNG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottoneLive)
                .addContainerGap(572, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bottoneIndietroComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_bottoneIndietroComponentHidden

    }//GEN-LAST:event_bottoneIndietroComponentHidden

    private void bottoneIndietrotornaIndietro(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneIndietrotornaIndietro
        this.setVisible(false);
        new StartMenu().setVisible(true);
    }//GEN-LAST:event_bottoneIndietrotornaIndietro

    private void bottoneResetComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_bottoneResetComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_bottoneResetComponentHidden

    private void bottoneReset(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneReset
        this.punti.clear();
        
        this.fioccoFinale.clear();
        
        this.forma.reset();
        this.triangoloFinale.reset();
        this.areaForma.reset();
        this.areaTriangoloOriginale.reset();
        this.areaTriangoloRitagliato.reset();
        
        this.disegnaForma = true;
        this.disegnaTriangoloOriginale = true;
        this.disegnaTriangoloFinito = true;
        
        this.disegnaFiocco = false;
        
       
        repaint();
    }//GEN-LAST:event_bottoneReset

    private void bottoneSalvaComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_bottoneSalvaComponentHidden
        
    }//GEN-LAST:event_bottoneSalvaComponentHidden

    private void bottoneSalva(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneSalva
        salvaSuNuovoFile();
    }//GEN-LAST:event_bottoneSalva

    private void bottoneTagliaComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_bottoneTagliaComponentHidden

    }//GEN-LAST:event_bottoneTagliaComponentHidden

    private void bottoneTaglia(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneTaglia
        tagliaTriangoloConBottone();
    }//GEN-LAST:event_bottoneTaglia

    private void padreRidimensionato(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_padreRidimensionato
        
    }//GEN-LAST:event_padreRidimensionato

    private void panelRidimensionato(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelRidimensionato
        
    }//GEN-LAST:event_panelRidimensionato

    private void bottoneGeneraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneGeneraActionPerformed
        disegnaFiocco = true;
        
        repaint();
    }//GEN-LAST:event_bottoneGeneraActionPerformed

    private void bottonePNGComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_bottonePNGComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_bottonePNGComponentHidden

    private void bottonePNG(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottonePNG

    }//GEN-LAST:event_bottonePNG

    private void bottoneLiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneLiveActionPerformed
        generazioneLive = !generazioneLive;
        if(!generazioneLive){
            fioccoFinale.clear();
            disegnaFiocco = false;
        }
        repaint();
    }//GEN-LAST:event_bottoneLiveActionPerformed

    /**
     * Metodo utile per chiedere all'utente dove
     * salvare i punti, e infine salvarli.
     */
    private void salvaSuNuovoFile() {
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int ris = fileChooser.showOpenDialog(this);
        if (ris == JFileChooser.APPROVE_OPTION) {
            String s = fileChooser.getSelectedFile().toString() + ".csv";
            Path filePath = Paths.get(s);
            File selectedFile = filePath.toFile();
            System.out.println("Selected file: " + s + ".csv");
            this.percorsoSalvataggioPunti = selectedFile.getAbsolutePath();
            try (PrintWriter writer = new PrintWriter(new File(percorsoSalvataggioPunti))) {
                StringBuilder sb = new StringBuilder();
                System.out.println(puntiDaSalvare.size());
                for(int i = 0; i < puntiDaSalvare.size(); i++){
                    sb.append(puntiDaSalvare.get(i).x);
                    sb.append(',');
                    sb.append(puntiDaSalvare.get(i).y);
                    sb.append('\n'); 
                }
              writer.write(sb.toString());
            }catch (FileNotFoundException e) {
              System.out.println(e.getMessage());
            }
            this.punti.clear();
            this.areaForma = new Area();
        } 
    }
    
    /**
     * Metodo utile per salvare i punti sullo stesso file. 
     * Viene invocato ad ogni ritaglio del triangolo
     */
    private void salvaSuStessoFile() {
        if(!(this.percorsoSalvataggioPunti.isEmpty())){
            try (PrintWriter writer = new PrintWriter(new File(percorsoSalvataggioPunti) ) ) {
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < punti.size(); i++){
                        sb.append(punti.get(i).x);
                        sb.append(',');
                        sb.append(punti.get(i).y);
                        sb.append('\n'); 
                        
                    }
                  writer.write(sb.toString());
            }catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } 
    }
      
    /**
     * Metodo utile per tagliare il triangolo.
     */
    public void tagliaTriangoloConBottone(){
        if(this.punti.size()>=3){
            this.areaTriangoloOriginale.subtract(areaForma);

            PathIterator iterator = areaTriangoloOriginale.getPathIterator(null);

            float[] floats = new float[6];
            while (!iterator.isDone()) {
                int type = iterator.currentSegment(floats);
                int x = (int) floats[0];
                int y = (int) floats[1];
                if(type != PathIterator.SEG_CLOSE) {
                    triangoloFinale.addPoint(x, y);
                }
                iterator.next();
            }

            this.areaTriangoloRitagliato = this.areaTriangoloOriginale;
            this.areaTriangoloOriginale = this.areaTriangoloRitagliato;
            
            if(fileCaricato){
                this.disegnaFiocco = true;
                this.disegnaTriangoloOriginale = true;
                this.disegnaTriangoloFinito = false;
            }else{
                this.areaForma.reset();
                this.forma.reset();
                this.punti.clear();
                
                areaForma = new Area();
                
                
                disegnaFiocco = false;
                this.disegnaTriangoloOriginale = false;
                this.disegnaTriangoloFinito = true;
            }   
            repaint();
            System.out.println("ho tagliato");
            
        }
    }
    
    /**
     * Metodo utile per tagliare il triangolo in live.
     */
    public void tagliaTriangoloInLive(){
        if(this.punti.size()>=3){
            disegnaFiocco = true;
            this.areaTriangoloOriginale.subtract(areaForma);

            PathIterator iterator = areaTriangoloOriginale.getPathIterator(null);

            float[] floats = new float[6];
            while (!iterator.isDone()) {
                int type = iterator.currentSegment(floats);
                int x = (int) floats[0];
                int y = (int) floats[1];
                if(type != PathIterator.SEG_CLOSE) {
                    triangoloFinale.addPoint(x, y);
                }
                iterator.next();
            }

            this.areaTriangoloRitagliato = this.areaTriangoloOriginale;
            this.areaTriangoloOriginale = this.areaTriangoloRitagliato;
            
            if(fileCaricato){
                this.disegnaFiocco = true;
                this.disegnaTriangoloOriginale = true;
                this.disegnaTriangoloFinito = false;
            }else{
                puntiDaSalvare = punti;
                salvaSuStessoFile();
                
                areaForma = new Area();
                this.disegnaTriangoloOriginale = true;
                this.disegnaTriangoloFinito = true;
                
            }   
            System.out.println("ho tagliato");    
        }
    }
    
    /**
     * Specchia il triangolo passato come parametro
     * 
     * @param a indica il triangolo da specchiare
     * @return il triangolo specchiato.
     */
    public Shape specchiaTriangolo(Area a) {
        AffineTransform ty2 = new AffineTransform();
        ty2.scale(-1, 1);
        AffineTransform ty3 = new AffineTransform();
        ty3.translate(-centroFiocco*2, 0);
        AffineTransform ty1 = new AffineTransform();
        ty1.concatenate(ty2);
        ty1.concatenate(ty3);
        return ty1.createTransformedShape(a);
    }
    
    /**
     * Ruota il triangolo passato
     * 
     * @param triangolo indica il triangolo da ruotare
     * @param angolo indica di quanti gradi si deve ruotare il triangolo.
     * @return 
     */
    public Shape ruotaTriangolo(Shape triangolo, double angolo) {
        AffineTransform tf = new AffineTransform();
        tf.rotate(Math.toRadians(angolo), this.triangolo.xpoints[2], this.triangolo.ypoints[2]);
        return tf.createTransformedShape(triangolo);
    }
    
    /**
     * Metodo utile per generare il fiocco con le dimensioni originali.
     */
    public void generaFiocco() {
        fioccoFinale.clear();
        Area prova = areaTriangoloRitagliato;
        for (int i = 0; i < 36; i += 6) {
            Shape flip = specchiaTriangolo(prova);
            addShape(ruotaTriangolo(flip, i * 10));
            addShape(ruotaTriangolo(areaTriangoloRitagliato, i * 10));
        }
    }
    
    /**
     * Rimpicciolisce il fiocco a metà.
     * 
     * @return il fiocco rimpicciolito.
     */
    public List<Shape> rimpicciolisciFiocco(){
        List<Shape> fioccoNuovo = new ArrayList<>();
        for (Shape shape : fioccoFinale) {
           AffineTransform tf = new AffineTransform();
           tf.scale(0.5, 0.5);
           fioccoNuovo.add(tf.createTransformedShape(shape));
        }
        return fioccoNuovo;
    }
    
    /**
     * Sposta il fiocco nella parte destra del panel.
     * 
     * @return il fiocco spostato.
     */
    public List<Shape> spostaFiocco(){
        
        List<Shape> fioccoNuovo = new ArrayList<>();
        for (Shape shape : rimpicciolisciFiocco()) {
           AffineTransform tf = new AffineTransform();
           tf.translate(panelCenter + bordoOrizzontale,100);
           fioccoNuovo.add(tf.createTransformedShape(shape));
        }
        return fioccoNuovo;
    }
    
    /**
     * Sposta un punto della forma di ritaglio (non implementato)
     * 
     * @param e indica l'evento del mouse.
     */
    private void spostaPunto(MouseEvent e) {
        for (int i = 0; i < punti.size(); i++) {
                if(e.getPoint().distance(punti.get(i)) <= raggio){
                    if(e.getX() > raggio && e.getY() > raggio){
                        if(e.getX() < this.getWidth() - raggio
                                && e.getY() < this.getHeight() - raggio){
                            //int index = this.punti.get(i);
                            this.punti.add(e.getPoint());
                            this.punti.remove(punti.get(i));
                            break;
                        }
                    }
                }
        }
        
        this.forma.reset();
        int[] x = new int[punti.size()];
        int[] y = new int[punti.size()];
        for (int i = 0; i < this.punti.size(); i++) {
            x[i] = this.punti.get(i).x;
            y[i] = this.punti.get(i).y;
            this.forma = new Polygon(x, y, this.punti.size());
        }
        repaint();
    }
    
    /**
     * Metodo che si occupa di disegnare nel panel.
     * 
     * @param g indica il contesto grafico
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g.create();
        
        g.setColor(coloreSfondoSinistro);
        g.fillRect(0,0,panelCenter,height);

        g.setColor(coloreSfondoDestro);
        g.fillRect(panelCenter,0,this.getWidth(),height);
        
        g.setColor(Color.black);
        g.drawLine(panelCenter, 0, panelCenter, height);

        forma.reset();
        for(int i = 0; i < punti.size();i++){
            Point p = punti.get(i);
            forma.addPoint(p.x, p.y);  
        }
        
        this.areaTriangoloOriginale = new Area(this.triangolo);
        this.areaForma = new Area(this.forma);
        
        if(this.disegnaTriangoloFinito){
            g2.setColor(coloreTriangoloOriginale);
            g2.fill(areaTriangoloRitagliato);
            if(disegnaFiocco){
                generaFiocco();
                for (Shape shape : spostaFiocco()) {
                    g2.setColor(coloreSfondoSinistro);
                    g2.fill(shape);
                }
                //disegnaFiocco = false;
            }
        }  
        
        if(this.disegnaTriangoloOriginale){
            g2.setColor(coloreTriangoloOriginale);
            g2.fill(areaTriangoloOriginale);
        }
        
        if(this.disegnaForma){
            for(int i = 0; i < punti.size(); i++){
                g2.setColor(coloreForma);
                g2.fill(areaForma);
                this.forma.reset(); 
                g.setColor(colorePunti);
                g.fillOval(punti.get(i).x-raggio, punti.get(i).y-raggio, raggio*2, raggio*2); 
                this.fileCaricato = false;
            }
        }  
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1 && e.getPoint().x <= this.getWidth()/2){
            addPoint(e.getPoint());
            if(generazioneLive){
                tagliaTriangoloInLive();   
                
            }
            
            
        }else if (e.getButton() == MouseEvent.BUTTON3  && e.getPoint().x <= this.getWidth()/2) {
            if (punti.size() > 0) {
                removeLastPoint();
                
                if(generazioneLive){
                    tagliaTriangoloInLive();
                    
                }
            }
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e){

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        spostaPunto(e);
        if(generazioneLive){
            tagliaTriangoloInLive();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        repaint();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bottoneGenera;
    private javax.swing.JButton bottoneIndietro;
    private javax.swing.JToggleButton bottoneLive;
    private javax.swing.JButton bottonePNG;
    private javax.swing.JButton bottoneReset;
    private javax.swing.JButton bottoneSalva;
    private javax.swing.JButton bottoneTaglia;
    // End of variables declaration//GEN-END:variables


 }
