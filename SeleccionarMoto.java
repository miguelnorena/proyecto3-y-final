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
    protected int costo;
    public Moto(String marca, String modelo, int cilindraje, int costo){
        this.marca = marca;
        this.modelo = modelo;
        this.cilindraje = cilindraje;
        this.costo = costo;
    }
    public abstract void mostrarDetalles();}
//javabean

class MotoDeportiva extends Moto{
    private boolean tieneCarenaje;

    public MotoDeportiva(String marca, String modelo, int cilindraje, int costo, boolean tieneCarenaje){
        super(marca, modelo, cilindraje, costo);
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
        System.out.println("la moto es: " + modelo + ", de la marca: " + marca + ", con un cilindraje de:" + cilindraje + ", tiene carenaje:" + tieneCarenaje + ", con un costo de: $" + costo);
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
    private JTextField costoField;
    private JComboBox<TipoMoto> tipoCombo;
    private Moto motoActual;

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

        add(new JLabel("costo de la motocicleta"));
        costoField = new JTextField(8);
        add(costoField);

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
JButton encenderBoton = new JButton("Enciende la moto");
encenderBoton.addActionListener(new ActionListener() {
    @Override 
    public void actionPerformed(ActionEvent e) {
if (motoActual != null) {
    motoActual.arrancar();
}else{
    JOptionPane.showMessageDialog(SeleccionarMoto.this, "primero debes agregar una moto");
}
    }
});
add(encenderBoton);

JButton detenerBoton = new JButton("detiene la moto");
detenerBoton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e){
        if(motoActual != null){
            motoActual.detener();
        }else{
            JOptionPane.showMessageDialog(SeleccionarMoto.this, "primero debes agregar una moto");
        }

    }
});
add(detenerBoton);
    } 
private void agregarMoto() {
    String marca = marcaField.getText();
    String modelo = modeloField.getText();
    String cilindrajeStr = cilindrajeField.getText();
    String costoStr = costoField.getText();

    if (marca.isEmpty() || modelo.isEmpty() || cilindrajeStr.isEmpty() || costoStr.isEmpty()) {
        JOptionPane.showMessageDialog(this,"es obligatorio llenar todos los campos" );
        return;
    }
    int cilindraje;
    int costo;
    try{
        cilindraje = Integer.parseInt(cilindrajeStr);
        costo = Integer.parseInt(costoStr);
    }catch (NumberFormatException e){
        JOptionPane.showMessageDialog(this, "El cilindraje y el costo tiene que ser un numero");
        return;
    }
    TipoMoto tipo = (TipoMoto) tipoCombo.getSelectedItem();
    Moto nuevaMoto = new MotoDeportiva ( marca, modelo, cilindraje, costo, true);
    nuevaMoto.mostrarDetalles();
    JOptionPane.showMessageDialog(this, "moto agregada:  " + marca + "  " + "" + modelo);
}
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        SeleccionarMoto app = new SeleccionarMoto();
        app.setVisible(true);
    });
}
}
