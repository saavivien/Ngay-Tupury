package src;

import javax.swing.*;
//import javax.swing.plaf.FontUIResource;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.*;
//import java.awt.font.FontRenderContext;
//import java.awt.peer.FontPeer;

public class MonTableau extends JFrame implements ActionListener {

    @SuppressWarnings("deprecation")
    private static final int HAND_CURSOR2 = HAND_CURSOR;
    public JMenuBar menuBar;
    public JMenu menu, menu1, menu2;
    public JPopupMenu jpm, jpm1;
    public static JMenuItem jmi1, jmi2, jmi3, jmi4, jmip1, jmip2, jmip3, jmip4, jmis1, jmis2;
    public int j;
    public boolean controlClic, controlSon;
    public MyButton banie, bouton10, bouton11, boutonJoueur1, boutonJoueur2;
    public JPanel panneau, panneauInterne1, panneauInterne2, panneauprinc;
    public JLabel lab2, lab3, lab4, lab5, lab6, lab7, lab8, labelTableau;
    public ImageIcon img10, img11, imgban;
    public static MyButton[] tabBouton;
    public static Jeu jeu;
    public static int monTour;
    public static int boutClicDroit;
    public static int nombreTour, n1, n2, n3, interm;
    public static int tabInter[];
    public Thread thread;
    public static URL url1, url2, url3;
    public static AudioClip ac1, ac2;

    public MonTableau() {
        super("Ngay");
        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(l);
        tabBouton = new MyButton[10];
        tabInter = new int[10];
        url1 = getClass().getResource("song/son3.wav");
        ac1 = Applet.newAudioClip(url1);
        url2 = getClass().getResource("song/son2.wav");
        ac2 = Applet.newAudioClip(url2);
        try {
//On force à utiliser le « look and feel » du système
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//Ici on force tous les composants de notre fenêtre (this) à se redessiner avec le « look and feel » du système
            SwingUtilities.updateComponentTreeUI(this);
        } catch (InstantiationException e) {
        } catch (ClassNotFoundException e) {
        } catch (UnsupportedLookAndFeelException e) {
        } catch (IllegalAccessException e) {
        }
    }

    public void nouvPartie() {
        controlClic = true;
        monTour = 0;
        nombreTour = 1;
        controlSon = true;
        Joueur j1 = new Joueur("saa");
        Joueur j2 = new Joueur("tadong");
        //j1.setNbrePion(30);
        //j2.setNbrePion(20);
        jeu = new Jeu(j1, j2);
        repaintTableau(jeu);
    }//désactiver les boutons

    public void activateButton(final Jeu jeu) {
        for (int i = 0; i < 10; i++) {
            if (monTour == 0 && jeu.permiDeJouer2(i) && jeu.peuJoueur1()) {
                tabBouton[i].setEnabled(false);
            }
            if (monTour == 1 && jeu.permiDeJouer1(i) && jeu.peuJoueur2()) {
                tabBouton[i].setEnabled(false);
            }
        }

    }

    public void indiqTour() {
        boutonJoueur1.setVisible(false);
        boutonJoueur2.setVisible(false);
        if (monTour == 1) {
            boutonJoueur2.setVisible(true);
        } else {
            boutonJoueur1.setVisible(true);
        }
    }

    //Cette fonction actualise le tableau au cours d'une partie
    public void repaintTableau(final Jeu jeu) {
        for (int i = 0; i < 10; i++) {
            tabBouton[i] = new MyButton(new ImageIcon(getClass().getResource("image/img" + jeu.getTabJeu()[i] + ".png")));
            tabBouton[i].addActionListener(this);
            tabBouton[i].setCursor(new Cursor(HAND_CURSOR2));
        }

        img10 = new ImageIcon(getClass().getResource("imgp/imgp" + jeu.getJoueur1().getNbrePion() + ".png"));
        bouton10 = new MyButton(img10);
        img11 = new ImageIcon(getClass().getResource("imgp/imgp" + jeu.getJoueur2().getNbrePion() + ".png"));
        bouton11 = new MyButton(img11);
        boutonJoueur1 = new MyButton(new ImageIcon(getClass().getResource("image/joueur1.png")));
        boutonJoueur2 = new MyButton(new ImageIcon(getClass().getResource("image/joueur2.png")));

        panneau = new JPanel();
        panneauprinc = new JPanel();
        panneauInterne1 = new JPanel();
        panneauInterne2 = new JPanel();
        panneauInterne1.setLayout(new GridLayout(5, 4, 4, 4));
        imgban = new ImageIcon(getClass().getResource("image/ban.png"));

        banie = new MyButton(imgban);
        lab2 = new JLabel("NOMBRE DE JEU :   " + nombreTour);
        lab3 = new JLabel();

        panneauInterne1.add(lab2);
        panneau.add(lab3);

        labelTableau = new JLabel(new ImageIcon(getClass().getResource("image/bg1.png")));
        labelTableau.add(tabBouton[9]);
        tabBouton[9].setBounds(12, 8, 100, 89);
        labelTableau.add(tabBouton[8]);
        tabBouton[8].setBounds(110, 8, 100, 89);
        labelTableau.add(tabBouton[7]);
        tabBouton[7].setBounds(208, 9, 100, 89);
        labelTableau.add(tabBouton[6]);
        tabBouton[6].setBounds(308, 9, 100, 89);
        labelTableau.add(tabBouton[5]);
        tabBouton[5].setBounds(403, 8, 100, 89);
        labelTableau.add(tabBouton[4]);
        tabBouton[4].setBounds(405, 111, 100, 89);
        labelTableau.add(tabBouton[3]);
        tabBouton[3].setBounds(309, 114, 100, 89);
        labelTableau.add(tabBouton[2]);
        tabBouton[2].setBounds(209, 113, 100, 89);
        labelTableau.add(tabBouton[1]);
        tabBouton[1].setBounds(109, 113, 100, 89);
        labelTableau.add(tabBouton[0]);
        tabBouton[0].setBounds(14, 113, 100, 89);

        //clic droit sur un bouton
        ActionListener actionclicdroit = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("vous êtes dans l'action listener");
                JMenuItem jmi = (JMenuItem) e.getSource();
                if (jmi == jmi1 && monTour == 0 && jeu.tabJeu[boutClicDroit] > 8) {

                    int interm = (jeu.getTabJeu()[boutClicDroit] + boutClicDroit - 7);

                    for (int i = 0; i < 10; i++) {
                        tabInter[i] = jeu.getTabJeu()[i];
                    }
                    jeu.jeuSimple1(jeu.getJoueur1(), boutClicDroit);
                    if (monTour == 0) {
                        monTour = 1 - monTour;
                    }
                    n1 = boutClicDroit;
                    n2 = interm;
                    thread = new Thread(new Distribution2());
                    thread.start();

                }
                if (jmi == jmi1 && monTour == 1 && jeu.tabJeu[boutClicDroit] > 8) {
                    int interm = jeu.getTabJeu()[boutClicDroit] + boutClicDroit - 7;
                    for (int i = 0; i < 10; i++) {
                        tabInter[i] = jeu.getTabJeu()[i];
                    }
                    jeu.jeuSimple2(jeu.getJoueur2(), boutClicDroit);
                    if (monTour == 1) {
                        monTour = 1 - monTour;
                    }
                    n1 = boutClicDroit;
                    n2 = interm;
                    thread = new Thread(new Distribution2());
                    thread.start();
                }
                if (jmi == jmi2 && monTour == 0) {
                    jeu.jeuDouble1(jeu.getJoueur1(), boutClicDroit);
                    tabBouton[boutClicDroit].setIcon(new ImageIcon(getClass().getResource("image/img" + jeu.tabJeu[boutClicDroit] + ".png")));
                }
                if (jmi == jmi2 && monTour == 1) {
                    jeu.jeuDouble2(jeu.getJoueur2(), boutClicDroit);
                    tabBouton[boutClicDroit].setIcon(new ImageIcon(getClass().getResource("image/img" + jeu.tabJeu[boutClicDroit] + ".png")));
                }
                if (jmi == jmi3 && monTour == 0) {
                    jeu.jeuRecompense1(jeu.getJoueur1(), boutClicDroit);
                    tabBouton[boutClicDroit].setIcon(new ImageIcon(getClass().getResource("image/img" + jeu.tabJeu[boutClicDroit] + ".png")));
                    tabBouton[boutClicDroit].repaint();
                }
                if (jmi == jmi3 && monTour == 1) {
                    jeu.jeuRecompense2(jeu.getJoueur2(), boutClicDroit);
                    tabBouton[boutClicDroit].setIcon(new ImageIcon(getClass().getResource("image/img" + jeu.tabJeu[boutClicDroit] + ".png")));
                    tabBouton[boutClicDroit].repaint();
                }

                if (jmi == jmi4 && monTour == 0 && jeu.tabJeu[boutClicDroit] > 6) {

                    int interm = (jeu.getTabJeu()[boutClicDroit] + boutClicDroit - 5);

                    for (int i = 0; i < 10; i++) {
                        tabInter[i] = jeu.getTabJeu()[i];
                    }
                    jeu.jeuSimpleRecup1(jeu.getJoueur1(), boutClicDroit);
                    if (monTour == 0) {
                        monTour = 1 - monTour;
                    }
                    n1 = boutClicDroit;
                    n2 = interm;
                    thread = new Thread(new Distribution2());
                    thread.start();

                }
                if (jmi == jmi4 & monTour == 1 && jeu.tabJeu[boutClicDroit] > 6) {
                    int interm = jeu.getTabJeu()[boutClicDroit] + boutClicDroit - 5;
                    for (int i = 0; i < 10; i++) {
                        tabInter[i] = jeu.getTabJeu()[i];
                    }
                    jeu.jeuSimpleRecup2(jeu.getJoueur2(), boutClicDroit);
                    if (monTour == 1) {
                        monTour = 1 - monTour;
                    }
                    n1 = boutClicDroit;
                    n2 = interm;
                    thread = new Thread(new Distribution2());
                    thread.start();
                }
            }
        };

        jpm = new JPopupMenu();
        jmi1 = new JMenuItem("''Keege  nene''/Gains à intérêt");
        jmi1.addActionListener(actionclicdroit);
        jmi2 = new JMenuItem("''Deege pan coore lin''/Rentréé d'un grenier");
        jmi2.addActionListener(actionclicdroit);
        jmi3 = new JMenuItem("''Ma' nen wer pan coore''/Récompense");
        jmi3.addActionListener(actionclicdroit);
        jmi4 = new JMenuItem("'Ferge nen tuu''/Regain de trou perdu");
        jmi4.addActionListener(actionclicdroit);
        jpm.add(jmi1);
        jpm.addSeparator();
        jpm.add(jmi2);
        jpm.addSeparator();
        jpm.add(jmi3);
        jpm.addSeparator();
        jpm.add(jmi4);
        //clic droit sur un bouton

        tabBouton[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON3) {
                    boutClicDroit = 0;
                    jmi1.setEnabled(false);
                    jmi2.setEnabled(false);
                    jmi3.setEnabled(false);
                    jmi4.setEnabled(false);       	        //activer les menus contextuels
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimple() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimple() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseDouble() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseDouble() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseRecompense() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseRecompense() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimplerecup() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimplerecup() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    jpm.show(tabBouton[0], event.getX(), event.getY());

                }
            }
        });
        tabBouton[1].addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON3) {
                    boutClicDroit = 1;
                    jmi1.setEnabled(false);
                    jmi2.setEnabled(false);
                    jmi3.setEnabled(false);
                    jmi4.setEnabled(false);
                    //activer les menus contextuels
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimple() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimple() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseDouble() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseDouble() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseRecompense() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseRecompense() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimplerecup() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimplerecup() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    jpm.show(tabBouton[1], event.getX(), event.getY());
                }
            }
        });
        tabBouton[2].addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON3) {
                    boutClicDroit = 2;
                    jmi1.setEnabled(false);
                    jmi2.setEnabled(false);
                    jmi3.setEnabled(false);
                    jmi4.setEnabled(false);
                    //activer les menus contextuels
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimple() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimple() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseDouble() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseDouble() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseRecompense() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseRecompense() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimplerecup() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimplerecup() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    jpm.show(tabBouton[2], event.getX(), event.getY());
                }
            }
        });
        tabBouton[3].addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON3) {
                    boutClicDroit = 3;
                    jmi1.setEnabled(false);
                    jmi2.setEnabled(false);
                    jmi3.setEnabled(false);
                    jmi4.setEnabled(false);
                    //activer les menus contextuels
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimple() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimple() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseDouble() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseDouble() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseRecompense() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseRecompense() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimplerecup() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimplerecup() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    jpm.show(tabBouton[3], event.getX(), event.getY());
                }
            }
        });
        tabBouton[4].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON3) {
                    boutClicDroit = 4;
                    jmi1.setEnabled(false);
                    jmi2.setEnabled(false);
                    jmi3.setEnabled(false);
                    jmi4.setEnabled(false);
                    //activer les menus contextuels
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimple() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimple() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseDouble() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseDouble() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseRecompense() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseRecompense() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimplerecup() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimplerecup() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    jpm.show(tabBouton[4], event.getX(), event.getY());
                }
            }
        });
        tabBouton[5].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON3) {
                    boutClicDroit = 5;
                    jmi1.setEnabled(false);
                    jmi2.setEnabled(false);
                    jmi3.setEnabled(false);
                    jmi4.setEnabled(false);
                    //activer les menus contextuels
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimple() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimple() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseDouble() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseDouble() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseRecompense() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseRecompense() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimplerecup() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimplerecup() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    jpm.show(tabBouton[5], event.getX(), event.getY());
                }
            }
        });
        tabBouton[6].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON3) {
                    boutClicDroit = 6;
                    jmi1.setEnabled(false);
                    jmi2.setEnabled(false);
                    jmi3.setEnabled(false);
                    jmi4.setEnabled(false);
                    //activer les menus contextuels
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimple() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimple() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseDouble() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseDouble() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseRecompense() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseRecompense() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimplerecup() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimplerecup() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    jpm.show(tabBouton[6], event.getX(), event.getY());
                }
            }
        });
        tabBouton[7].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON3) {
                    boutClicDroit = 7;
                    jmi1.setEnabled(false);
                    jmi2.setEnabled(false);
                    jmi3.setEnabled(false);
                    jmi4.setEnabled(false);
                    //activer les menus contextuels
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimple() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimple() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseDouble() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseDouble() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseRecompense() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseRecompense() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimplerecup() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimplerecup() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    jpm.show(tabBouton[7], event.getX(), event.getY());
                }
            }
        });
        tabBouton[8].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON3) {
                    boutClicDroit = 8;
                    jmi1.setEnabled(false);
                    jmi2.setEnabled(false);
                    jmi3.setEnabled(false);
                    jmi4.setEnabled(false);
                    //activer les menus contextuels
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimple() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimple() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseDouble() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseDouble() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseRecompense() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseRecompense() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimplerecup() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimplerecup() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    jpm.show(tabBouton[8], event.getX(), event.getY());
                }
            }
        });
        tabBouton[9].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON3) {
                    boutClicDroit = 9;
                    jmi1.setEnabled(false);
                    jmi2.setEnabled(false);
                    jmi3.setEnabled(false);
                    jmi4.setEnabled(false);
                    //activer les menus contextuels
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimple() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimple() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi1.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseDouble() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseDouble() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi2.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseRecompense() >= 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseRecompense() >= 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi3.setEnabled(true);
                    }
                    if (monTour == 0 && jeu.getJoueur1().getCaseSimplerecup() == 1 && jeu.permiDeJouer1(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    if (monTour == 1 && jeu.getJoueur2().getCaseSimplerecup() == 1 && jeu.permiDeJouer2(boutClicDroit)) {
                        jmi4.setEnabled(true);
                    }
                    jpm.show(tabBouton[9], event.getX(), event.getY());
                }
            }
        });

        activateButton(jeu);
        panneauInterne2.add(labelTableau);
        labelTableau.setBounds(0, 0, 150, 200);
        jmip1 = new JMenuItem("quitter");
        jmip2 = new JMenuItem("help");
        jmip3 = new JMenuItem("about");
        jmip4 = new JMenuItem("nouvelle partie");
        jmis1 = new JMenuItem("activer son");
        jmis2 = new JMenuItem("désactiver son");
        jmip1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        jmip4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                nouvPartie();
            }
        });
        jmip2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Aide();
            }
        });
        jmip3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Aide();
            }
        });
        jmis1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                controlSon = true;
            }
        });
        jmis2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                controlSon = false;
            }
        });
        //.add

        panneau.setLayout(null);
        panneau.add(banie);
        panneau.add(boutonJoueur1);
        boutonJoueur1.setBounds(20, 90, 179, 92);
        panneau.add(boutonJoueur2);
        boutonJoueur2.setBounds(720, 90, 179, 92);
        banie.setBounds(180, 0, 600, 110);
        indiqTour();
        panneau.add(panneauInterne2);
        panneauInterne2.setBounds(215, 200, 510, 260);
        panneau.add(lab2);
        lab2.setBounds(400, 450, 200, 50);
        panneau.add(bouton10);
        bouton10.setBounds(10, 200, 210, 322);
        panneau.add(bouton11);
        bouton11.setBounds(720, 200, 194, 322);
        panneau.setBounds(25, 5, 950, 520);
        panneauprinc.setLayout(null);
        panneauprinc.add(panneau);
        panneauprinc.setBackground(new Color(20));
        setContentPane(panneauprinc);
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        menu = new JMenu("Fichier");
        menu1 = new JMenu("Help");
        menu2 = new JMenu("Son");
        menu.add(jmip1);
        menu.addSeparator();
        menu.add(jmip4);
        menu1.add(jmip2);
        menu1.addSeparator();
        menu1.add(jmip3);
        menu2.add(jmis1);
        menu1.addSeparator();
        menu2.add(jmis2);
        menuBar.add(menu);
        menuBar.add(menu1);
        menuBar.add(menu2);
        ImageIcon imgb3 = new ImageIcon(getClass().getResource("image/imgban3.png"));
        this.setIconImage(imgb3.getImage());
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (jeu.jeuTermine()) {
            jeu.finaliserJeu();
            Jeu jeuTrans = jeu;
            if (jeu.getJoueur1().getNbrePion() > 47) {
                JOptionPane jop = new JOptionPane();
                ImageIcon imgban1 = new ImageIcon(getClass().getResource("image/ban1.png"));
                String msg1 = "la partie est terminé et le vainceur est joueur 1\n Nombre de pion joueur1 = " + (jeuTrans.getJoueur1().getNbrePion()) + "\n Nombre de pion joueur2 = " + (jeuTrans.getJoueur2().getNbrePion());
                JOptionPane.showMessageDialog(this, msg1, "À propos", JOptionPane.INFORMATION_MESSAGE, imgban1);

            } else if (jeu.getJoueur2().getNbrePion() > 47) {
                JOptionPane jop = new JOptionPane();
                ImageIcon imgban1 = new ImageIcon(getClass().getResource("image/ban1.png"));
                String msg1 = "la partie est terminé et le vainceur est le joueur 2 \n Nombre de pion joueur1 = " + (jeuTrans.getJoueur1().getNbrePion()) + "\n Nombre de pion joueur2 = " + (jeuTrans.getJoueur2().getNbrePion());
                JOptionPane.showMessageDialog(this, msg1, "À propos", JOptionPane.INFORMATION_MESSAGE, imgban1);

            } else {
                monTour = 0;
                nombreTour++;
                jeu = new Jeu(jeuTrans.getJoueur1(), jeuTrans.getJoueur2());
                if (jeu.getJoueur2().getCaseDouble() >= 1 || jeu.getJoueur2().getCaseSimple() == 1) {
                    monTour = 1;
                }
                JOptionPane jop = new JOptionPane();
                ImageIcon imgban1 = new ImageIcon(getClass().getResource("image/ban1.png"));
                String msg1 = " Le " + ((nombreTour) - 1) + " e jeu est terminé \n Nombre de pion joueur1 = " + (jeuTrans.getJoueur1().getNbrePion()) + "\n Nombre de pion joueur2 = " + (jeuTrans.getJoueur2().getNbrePion());
                JOptionPane.showMessageDialog(this, msg1, "À propos", JOptionPane.INFORMATION_MESSAGE, imgban1);

                repaintTableau(jeu);
            }
        } else {
            Object source = e.getSource();
            if (source instanceof JButton && controlClic) {
                JButton boutonsource = (JButton) source;
                for (int j = 0; j < 10; j++) {
                    if (boutonsource == tabBouton[j] && jeu.peutDistribuer(j)) {
                        if (jeu.permiDeJouer1(j) && jeu.getJoueur1().getCaseDouble() == 0 && jeu.getJoueur1().getCaseRecompense() == 0) {
                            int interm = jeu.getTabJeu()[j] + j;
                            for (int i = 0; i < 10; i++) {
                                tabInter[i] = jeu.getTabJeu()[i];
                            }
                            jeu.distribuer(j, jeu.getJoueur1());
                            if (monTour == 0 && jeu.peuJoueur2()) {
                                monTour = 1 - monTour;
                            }
                            n1 = j;
                            n2 = interm;
                            thread = new Thread(new Distribution2());
                            thread.start();
                        } else if (jeu.permiDeJouer2(j) && jeu.getJoueur2().getCaseDouble() == 0 && jeu.getJoueur2().getCaseRecompense() == 0) {
                            int interm = jeu.getTabJeu()[j] + j;
                            for (int i = 0; i < 10; i++) {
                                tabInter[i] = jeu.getTabJeu()[i];
                            }
                            jeu.distribuer(j, jeu.getJoueur2());
                            if (monTour == 1 && jeu.peuJoueur1()) {
                                monTour = 1 - monTour;
                            }
                            n1 = j;
                            n2 = interm;
                            thread = new Thread(new Distribution2());
                            thread.start();
                        } else {
                            JOptionPane jop = new JOptionPane();
                            ImageIcon imgban1 = new ImageIcon(getClass().getResource("image/ban1.png"));
                            String msg1 = "Vous devez d'abord choisir une case pour pour la rentrée du grenier \n et/ou "
                                    + "la récompense pour le bon jeu";

                            JOptionPane.showMessageDialog(this, msg1, "À propos", JOptionPane.INFORMATION_MESSAGE, imgban1);
                        }
                    }
                }
            }
        }
    }

    public class Distribution2 implements Runnable {

        @Override
        public void run() {
            controlClic = false;
            for (int i = 0; i < 10; i++) {
                tabBouton[i].setEnabled(true);
            }
            tabInter[n1] = -1;
            for (int i = n1; i <= n2; i++) {
                int i1 = i % 10;
                tabInter[i1]++;
                tabBouton[i1].setIcon(new ImageIcon(getClass().getResource("image/img" + tabInter[i1] + ".png")));
                tabBouton[i1].repaint();
                try {
                    if (controlSon) {
                        ac1.play();
                        Thread.sleep(300);
                        ac1.stop();
                    } else {
                        Thread.sleep(300);
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (jeu.tabJeu[n2 % 10] == 0) {
                tabBouton[n2 % 10].setIcon(new ImageIcon(getClass().getResource("image/img" + jeu.getTabJeu()[n2 % 10] + ".png")));
                tabBouton[n2 % 10].repaint();
                try {
                    if (controlSon) {
                        ac2.play();
                        Thread.sleep(300);
                        ac2.stop();
                    } else {
                        Thread.sleep(300);
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            n2++;
            while (jeu.tabJeu[n2 % 10] == 0 && (n2 % 10) != 0 && (n2 % 10) != 5 && tabInter[n2 % 10] != 0) {
                tabBouton[n2 % 10].setIcon(new ImageIcon(getClass().getResource("image/img" + jeu.getTabJeu()[n2 % 10] + ".png")));
                tabBouton[n2 % 10].repaint();
                try {
                    if (controlSon) {
                        ac2.play();
                        Thread.sleep(300);
                        ac2.stop();
                    } else {
                        Thread.sleep(300);
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                n2++;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            activateButton(jeu);
            panneauInterne1.setEnabled(true);

            bouton10.setIcon(new ImageIcon(getClass().getResource("imgp/imgp" + jeu.getJoueur1().getNbrePion() + ".png")));
            bouton10.repaint();
            bouton11.setIcon(new ImageIcon(getClass().getResource("imgp/imgp" + jeu.getJoueur2().getNbrePion() + ".png")));
            bouton11.repaint();

            controlClic = true;
            indiqTour();
        }
    }

    public static void main(String[] args) {

        MonTableau frame = new MonTableau();
        frame.nouvPartie();
    }
}
