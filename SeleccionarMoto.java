import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//interace
interface Manejable{
    void arrancar();
    void detener();
}

//clase abastracta
abstract class Moto implements Manejable{
    protected String marca;
    protected String modelo;
    protected int cilindraje;

    public Moto(String marca, String modelo, int cilindraje){
        this.marca = marca;
        this.modelo = modelo;
        this.cilindraje = cilindraje;
    }
    public abstract void mostrarDetalles();}
//javabean

class MotoDeportiva extends Moto{
    private boolean tieneCarenaje;

    public MotoDeportiva(String marca, String modelo, int cilindraje, boolean tieneCarenaje){
        super(marca, modelo, cilindraje);
        this.tieneCarenaje = tieneCarenaje;
    }

    @Override
    public void arrancar() {
    System.out.println("la moto deportiva" + modelo + "esta arrancando");
    }
    @Override
    public void detener(){
        System.out.println("la moto" + modelo + "ha sido apagada con exito");
    }
    @Override
    public void mostrarDetalles() {
        System.out.println("la moto es: " + modelo + ", de la marca: " + marca + ", con un cilindraje de:" + cilindraje + ", tiene carenaje:" + tieneCarenaje);
    }
    }
//enumeracion
enum TipoMoto{
    DEPORTIVA,TODOTERRENO, SCOOTER, SEMIAUTOMATICA
}
//parte de interface
public class SeleccionarMoto extends JFrame{
    private JTextField marcaField;
    private JTextField modeloField;
    private JTextField cilindrajeField;
    private JComboBox<TipoMoto> tipoCombo;
    
    public SeleccionarMoto(){
        setTitle("seleccionador de motos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout( new FlowLayout());

        add(new JLabel ("marca"));
        marcaField = new JTextField(15);
        add(marcaField);

        add(new JLabel("modelo"));
        modeloField = new JTextField(15);
        add(modeloField);

        add(new JLabel("cilindraje"));
        cilindrajeField = new JTextField(5);
        add(cilindrajeField);

        add(new JLabel("Tipo de moto"));
        tipoCombo = new JComboBox<>(TipoMoto.values());
        add(tipoCombo);

        JButton agregarBoton = new JButton ("agrega moto");
        agregarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarMoto();
            }
        });
        add (agregarBoton);
    } 
private void agregarMoto() {
    String marca = marcaField.getText();
    String modelo = modeloField.getText();
    String cilindrajeStr = cilindrajeField.getText();

    if (marca.isEmpty() || modelo.isEmpty() || cilindrajeStr.isEmpty()) {
        JOptionPane.showMessageDialog(this,"es obligatorio llenar todos los campos" );
        return;
    }
    int cilindraje;
    try{
        cilindraje = Integer.parseInt(cilindrajeStr);
    }catch (NumberFormatException e){
        JOptionPane.showMessageDialog(this, "El cilindraje tiene que ser un numero");
        return;
    }
    TipoMoto tipo = (TipoMoto) tipoCombo.getSelectedItem();
    Moto nuevaMoto = new MotoDeportiva (
        marca, modelo, cilindraje, true); // Por simplicidad, asumimos que es una moto deportiva
    
    nuevaMoto.mostrarDetalles();
    JOptionPane.showMessageDialog(this, "moto agregada: " + marca + "  " + "" + modelo);
}
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        SeleccionarMoto app = new SeleccionarMoto();
        app.setVisible(true);
    });
}
}