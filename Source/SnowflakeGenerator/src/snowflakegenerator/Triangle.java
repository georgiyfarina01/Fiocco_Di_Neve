package snowflakegenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;

/**
 *
 * @author Giorgio
 */
public class Triangle extends javax.swing.JPanel implements MouseListener{
    
    /**
     * Il colore dello sfondo del programma.
     */
    private Color coloreSfondo = new Color(204,255,255);
    
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
    private int raggio = 5;

    
    /**
     * Larghezza del panel del triangolo.
     */
    private int width = 504;
    
    /**
     * Altezza del panel del triangolo.
     */
    private int height = 729;
    
    /**
     * Punti della forma che creiamo.
     */
    private List<Point> punti;
    
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
     * Coordinate x della forma che creiamo.
     */
    private int x[];
    
    /**
     * Coordinate y della forma che creiamo.
     */
    private int y[];
    
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
     * Poligono del triangolo ritagliato.
     */
    private Polygon fiocco = new Polygon();
    
    /**
     * Dimensioni minime del panel.
     */
    public static final int DIM_MIN[] = {504,729};
    
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
     * Creates new form FlakeManagement
     */
    public Triangle() {
        initComponents();
        this.punti = new ArrayList<>();
        
        this.addMouseListener(this);
        
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
    }
    
    /**
     * Metodo utile per togliere i punti della forma che creiamo.
     */
    private void removePunto(Point p){
        punti.remove(p);
    }
    
    /**
     * Metodo utile per aggiungere i punti della forma che creiamo.
     */
    private void addShape(Shape s){
        fioccoFinale.add(s);
    }
    
    /**
     * Metodo utile per togliere i punti della forma che creiamo.
     */
    private void removeShape(Shape s){
        fioccoFinale.remove(s);
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

        bottoneIndietro.setBackground(new java.awt.Color(255, 51, 51));
        bottoneIndietro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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

        bottoneReset.setBackground(new java.awt.Color(255, 51, 51));
        bottoneReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bottoneReset.setText("Reset");
        bottoneReset.setName(""); // NOI18N
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

        bottoneSalva.setBackground(new java.awt.Color(255, 51, 51));
        bottoneSalva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bottoneSalva.setText("Salva");
        bottoneSalva.setName(""); // NOI18N
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

        bottoneTaglia.setBackground(new java.awt.Color(255, 51, 51));
        bottoneTaglia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bottoneTaglia.setText("Taglia");
        bottoneTaglia.setName(""); // NOI18N
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bottoneIndietro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bottoneReset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bottoneSalva, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bottoneTaglia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bottoneIndietro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bottoneReset, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bottoneSalva, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bottoneTaglia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(717, Short.MAX_VALUE))
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
        for(int i = 0; i < this.x.length; i++){
            this.x[i] = 0;
        }
        for(int j = 0; j < this.y.length; j++){
            this.y[j] = 0;
        }
        this.forma.reset();
        this.triangoloFinale.reset();
        this.fiocco.reset();
        
        this.areaForma.reset();
        this.areaTriangoloOriginale.reset();
        this.areaTriangoloRitagliato.reset();
        
        this.disegnaForma = true;
        this.disegnaTriangoloOriginale = true;
        this.disegnaTriangoloFinito = true;
       
        repaint();
    }//GEN-LAST:event_bottoneReset

    private void bottoneSalvaComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_bottoneSalvaComponentHidden
        
    }//GEN-LAST:event_bottoneSalvaComponentHidden

    private void bottoneSalva(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneSalva
        /*JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int ris = fileChooser.showOpenDialog(this);
        if (ris == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }*/
    }//GEN-LAST:event_bottoneSalva

    private void bottoneTagliaComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_bottoneTagliaComponentHidden

    }//GEN-LAST:event_bottoneTagliaComponentHidden

    private void bottoneTaglia(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottoneTaglia
        if(this.punti.size()>1){
             this.areaTriangoloOriginale.subtract(this.areaForma);

            PathIterator iterator = areaTriangoloOriginale.getPathIterator(null);

            float[] floats = new float[6];
            int i = 0;
            while (!iterator.isDone()) {
                i++;
                int type = iterator.currentSegment(floats);
                int x = (int) floats[0];
                int y = (int) floats[1];
                if(type != PathIterator.SEG_CLOSE) {
                    triangoloFinale.addPoint(x, y);
                    //System.out.println(i);
                }
                iterator.next();
            }

            this.disegnaForma = false;
            this.disegnaTriangoloOriginale = false;
            this.areaTriangoloRitagliato = this.areaTriangoloOriginale;
            System.out.println("ho generato");
            repaint();
        }
    }//GEN-LAST:event_bottoneTaglia

    private void padreRidimensionato(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_padreRidimensionato
        ridimensiona();
    }//GEN-LAST:event_padreRidimensionato

    private void panelRidimensionato(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_panelRidimensionato
        ridimensiona();
    }//GEN-LAST:event_panelRidimensionato
    
    /**
     * Metodo utile per adattare la posizione del triangolo in maniera corretta.
     */
    public void ridimensiona() {
        
        this.triangolo.reset();
        this.width = this.getWidth()/2;
        this.height = this.getHeight();
        System.out.println(this.width);
        System.out.println(this.height);
        this.catetoMaggiore = this.height/2;
        this.catetoMinore = (int)(this.catetoMaggiore/Math.sqrt(3));
        this.ipotenusa = this.catetoMinore*2;
        
        int diffX = this.width - DIM_MIN[0];
        if(this.width > DIM_MIN[0]){
            this.bordoOrizzontale = 100 + diffX/2;     
        }
        if(this.height > DIM_MIN[1]){

        }
        if(this.width == DIM_MIN[0] && this.height == DIM_MIN[1]){
            System.out.println("niente");
            this.bordoOrizzontale = 100;
            
        }
        //this.triangolo.reset();
        this.triangolo.addPoint(this.bordoOrizzontale, this.height/4);
        this.triangolo.addPoint(this.bordoOrizzontale+this.catetoMinore, this.height/4);
        this.triangolo.addPoint(this.bordoOrizzontale+this.catetoMinore, this.height/4 + this.catetoMaggiore);
        centroFiocco = triangolo.xpoints[2];
        
        
        /*this.triangoloFinale.addPoint(this.bordoOrizzontale, this.height/4);
        this.triangoloFinale.addPoint(this.bordoOrizzontale+this.catetoMinore, this.height/4);
        this.triangoloFinale.addPoint(this.bordoOrizzontale+this.catetoMinore, this.height/4 + this.catetoMaggiore);*/
        
        
        repaint();
    }
    
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
    
    public Shape ruotaTriangolo(Shape fiocco, double angolo) {
        AffineTransform tf = new AffineTransform();
        tf.rotate(Math.toRadians(angolo), triangolo.xpoints[2], triangolo.ypoints[2]);
        return tf.createTransformedShape(fiocco);
    }
    
    public List<Shape> generaFiocco() {
        fioccoFinale.clear();
        Area prova = areaTriangoloRitagliato;
        for (int i = 0; i < 36; i += 6) {
            Shape flip = specchiaTriangolo(prova);
            addShape(ruotaTriangolo(flip, i * 10));
            addShape(ruotaTriangolo(areaTriangoloRitagliato, i * 10));
        }
        return fioccoFinale;
    }
    
    public List<Shape> rimpicciolisciFiocco(){
        List<Shape> fioccoNuovo = new ArrayList<>();
        for (Shape shape : fioccoFinale) {
           AffineTransform tf = new AffineTransform();
           tf.scale(0.5, 0.5);
           fioccoNuovo.add(tf.createTransformedShape(shape));
        }
        return fioccoNuovo;
    }
    
    public List<Shape> spostaFiocco(){
        int xIniziale = width;
        List<Shape> fioccoNuovo = new ArrayList<>();
        for (Shape shape : rimpicciolisciFiocco()) {
           AffineTransform tf = new AffineTransform();
           tf.translate(600,100);
           fioccoNuovo.add(tf.createTransformedShape(shape));
        }
        return fioccoNuovo;
    }
    

    private void ridimensionaForma() {
        AffineTransform tx = new AffineTransform();
        AffineTransform ty = new AffineTransform();
        AffineTransform tf = new AffineTransform();
        tx.translate(((double)this.width-(double)DIM_MIN[0]) /2 ,0);
        ty.scale((double)this.getHeight()/(double)DIM_MIN[1], (double)this.getHeight()/(double)DIM_MIN[1]);
        tf.concatenate(tx);
        tf.concatenate(ty);
        Shape transformed = tf.createTransformedShape(this.forma);

        PathIterator iterator = transformed.getPathIterator(null);
        this.forma.reset();
        float[] floats = new float[6];
        while (!iterator.isDone()) {
            int type = iterator.currentSegment(floats);
            int x = (int) floats[0];
            int y = (int) floats[1];
            if(type != PathIterator.SEG_CLOSE) {
                this.forma.addPoint(x, y);
                //System.out.println(i);
            }
            iterator.next();
        }
        
        
        System.out.println();
    }
    
    private Shape ridimensionaTriangoloRitagliato() {
        AffineTransform tx = new AffineTransform();
        AffineTransform ty = new AffineTransform();
        AffineTransform tf = new AffineTransform();
        tx.translate(((double)this.width-(double)DIM_MIN[0]) /2 ,0);
        ty.scale((double)this.getHeight()/(double)DIM_MIN[1], (double)this.getHeight()/(double)DIM_MIN[1]);
        tf.concatenate(tx);
        tf.concatenate(ty);
        Shape transformed = tf.createTransformedShape(areaTriangoloRitagliato);
        return transformed;

        /*PathIterator iterator = transformed.getPathIterator(null);
        this.forma.reset();
        float[] floats = new float[6];
        while (!iterator.isDone()) {
            int type = iterator.currentSegment(floats);
            int x = (int) floats[0];
            int y = (int) floats[1];
            if(type != PathIterator.SEG_CLOSE) {
                this.forma.addPoint(x, y);
                //System.out.println(i);
            }
            iterator.next();
        }*/
    }
    
    private void spostaPunto(MouseEvent e) {
        Point p = e.getPoint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g.create();
        
        g.setColor(coloreSfondo);
        g.fillRect(0,0,this.width, this.height);

        for(int i = 0; i < punti.size();i++){
            Point p = punti.get(i);
            x = new int[punti.size()];
            y = new int[punti.size()];
            x[i] = p.x;
            y[i] = p.y;
            forma.addPoint(p.x+raggio, p.y+raggio);  
        }
        
        this.areaTriangoloOriginale = new Area(this.triangolo);
        this.areaForma = new Area(this.forma);
        
        
        if(this.disegnaTriangoloOriginale){
            g.setColor(coloreTriangoloOriginale);
            g.fillPolygon(this.triangolo);
        }
        
        
        if(this.disegnaForma){
            ridimensionaForma();
            for(int i = 0; i < punti.size(); i++){
                if(i > 1){
                    //g.drawLine(punti.get(i).x+5, punti.get(i).y+5, punti.get(i-1).x+5, punti.get(i-1).y+5);
                    g.setColor(coloreForma);
                    g.fillPolygon(forma);
                    this.forma.reset();

                }            
                g.setColor(colorePunti);
                g.fillOval(punti.get(i).x, punti.get(i).y, raggio*2, raggio*2);
            }
        }
        //ridimensionaTriangoloRitagliato();
        if(this.disegnaTriangoloFinito){
            //g.setColor(Color.BLACK);
            //g.fillPolygon(triangoloFinale);
            g2.setColor(Color.GREEN);
            g2.fill(areaTriangoloRitagliato);
            ridimensionaTriangoloRitagliato();
            //g2.setColor(Color.blue);
            generaFiocco();
            //if(fioccoFinale.size() > 2){
                boolean co = true;
                for (Shape shape : spostaFiocco()) {
                    if(co){
                        g2.setColor(Color.BLACK);
                    }else{
                        g2.setColor(Color.blue);
                    }
                    co = !co;
                    g2.fill(shape);
                }
            //}
            
            /*Shape prova;
            prova = flipArea(areaTriangoloRitagliato);
            g2.setColor(Color.blue);
            g2.fill(prova);*/
        }
        
        //g.setColor(Color.BLUE);
        //g.fillPolygon(fiocco);
        //float theta = 90;
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e){
        
        if(e.getButton() == MouseEvent.BUTTON1){
            addPoint(e.getPoint());
        }
        //}
        System.out.println("dentro " + e.getPoint());
        
        repaint();

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bottoneIndietro;
    private javax.swing.JButton bottoneReset;
    private javax.swing.JButton bottoneSalva;
    private javax.swing.JButton bottoneTaglia;
    // End of variables declaration//GEN-END:variables

 }
