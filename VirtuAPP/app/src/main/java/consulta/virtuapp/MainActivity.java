package consulta.virtuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    EditText codigo;
    ImageButton search;
    TextView idequipo;
    TextView tipo;
    TextView marca;
    TextView modelo;
    TextView serie;
    TextView activo;
    TextView estasig;
    TextView userasig;
    TextView fechaasig;
    TextView especs;
    TextView ram;
    TextView tipocpu;
    TextView fechacrea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        codigo=(EditText) findViewById(R.id.txtcode);
        idequipo=(TextView) findViewById(R.id.txtidequipo);
        tipo=(TextView) findViewById(R.id.txttipo);
        marca=(TextView) findViewById(R.id.txtmarca);
        modelo=(TextView) findViewById(R.id.txtmodelo);
        serie=(TextView) findViewById(R.id.txtserie);
        activo=(TextView) findViewById(R.id.txtactivo);
        estasig=(TextView) findViewById(R.id.txtestadoasg);
        userasig=(TextView) findViewById(R.id.txtuserasig);
        fechaasig=(TextView) findViewById(R.id.txtfechaasig);
        especs=(TextView) findViewById(R.id.txtespec);
        ram=(TextView) findViewById(R.id.txtram);
        tipocpu=(TextView) findViewById(R.id.txtcpu);
        fechacrea=(TextView) findViewById(R.id.txtfechacrea);

        search =(ImageButton) findViewById(R.id.btnbuscar);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        }

    public Connection conexionBD(){

        Connection con=null;

        try {

            StrictMode.ThreadPolicy politica=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            con= DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.86;databaseName=Virtu;user=gerar;password:618823;");

        }catch (Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
            
        }
        return con;

    }

    public void consultaequipo(){
        try{
            Statement stm=conexionBD().createStatement();
            ResultSet rs=stm.executeQuery("SELECT * FROM EQUIPO WHERE IDEquipo='"+ idequipo.getText().toString()+"'");
            if(rs.next()){
                idequipo.setText(rs.getString(2));
                tipo.setText(rs.getString(3));
                marca.setText(rs.getString(4));
                modelo.setText(rs.getString(5));
                serie.setText(rs.getString(6));
                activo.setText(rs.getString(7));
                estasig.setText(rs.getString(8));
                userasig.setText(rs.getString(9));
                fechaasig.setText(rs.getString(10));
                especs.setText(rs.getString(11));
                ram.setText(rs.getString(12));
                tipocpu.setText(rs.getString(13));
                fechacrea.setText(rs.getString(14));
            }
            idequipo.setText("");
        }
        catch (Exception e){

        }
    }

}