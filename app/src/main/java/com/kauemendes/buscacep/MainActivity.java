package com.kauemendes.buscacep;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.InputStream;


public class MainActivity extends ActionBarActivity {

    EditText txtCep;
    Button btnSearchCep;
    EditText txtResult;
    private ProgressBar barraProgresso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCep = (EditText) findViewById(R.id.txtCep);
        txtResult = (EditText) findViewById(R.id.txtResult);
        barraProgresso = (ProgressBar) findViewById(R.id.barraProgresso);
        btnSearchCep = (Button) findViewById(R.id.btnSearchCep);
        btnSearchCep.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TesteAsync().execute();
            }
        });
    }

    private String montarURL(String cep){
        return "http://cep.republicavirtual.com.br/web_cep.php?cep=".concat(cep).concat("&formato=json");
    }



    class TesteAsync extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        private String retorno;

        protected void onPreExecute() {
            progressDialog.setMessage("Consultado o CEP...");
            progressDialog.show();
            barraProgresso.setVisibility(0);
        }

        protected Void doInBackground(Void... params) {
            WebClient client = new WebClient(montarURL(txtCep.getText().toString()));
            retorno = client.obterEndereco();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            barraProgresso.setVisibility(4);
            Endereco endereco = new Endereco();

            if(endereco.parseJSON(String.valueOf(retorno)) != null){
                Endereco endParse = endereco.parseJSON(String.valueOf(retorno));
                if(endParse.getResultado() == 0){
                    txtResult.setText("Não foi encontrado registro referente ao CEP informado!");
                    txtResult.setGravity(1);
                }else{
                    txtResult.setText(
                            "Endereço encontrado:\n\nLogradouro: "+endParse.getTipoLogradouro()+" "+endParse.getLogradouro()+
                                    "\nBairro: "+endParse.getBairro()+
                                    "\nCidade: "+endParse.getCidade()+
                                    "\nUF: "+endParse.getUf());
                    txtResult.setGravity(0);
                }

            }else{
                txtResult.setText("Um erro ocorreu!=(\nNão foi possível obter informações sobre o CEP!");
                txtResult.setGravity(1);
            }

            progressDialog.dismiss();

        }

    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
