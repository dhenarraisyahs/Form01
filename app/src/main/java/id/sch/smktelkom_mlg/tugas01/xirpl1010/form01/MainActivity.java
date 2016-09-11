package id.sch.smktelkom_mlg.tugas01.xirpl1010.form01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    EditText etNama, etTahun;
    Button bOK;
    TextView tvHasil, tvHasil2, tvHasil3, tvPJ, tvHasil4;
    RadioGroup rgGender;
    CheckBox rpl, tkj, mlt;
    int nPJ;
    Spinner spKota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        etTahun = (EditText) findViewById(R.id.editTextTahun);

        bOK = (Button) findViewById(R.id.buttonOK);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);

        rgGender = (RadioGroup) findViewById(R.id.radioGroupGender);
        tvHasil2 = (TextView) findViewById(R.id.textViewHasil2);

        rpl = (CheckBox) findViewById(R.id.checkBoxRPL);
        tkj = (CheckBox) findViewById(R.id.checkBoxTKJ);
        mlt = (CheckBox) findViewById(R.id.checkBoxMLT);
        tvHasil3 = (TextView) findViewById(R.id.textViewHasil3);
        tvPJ = (TextView) findViewById(R.id.textViewPJ);

        rpl.setOnCheckedChangeListener(this);
        tkj.setOnCheckedChangeListener(this);
        mlt.setOnCheckedChangeListener(this);

        spKota = (Spinner) findViewById(R.id.spinnerKota);
        tvHasil4 = (TextView) findViewById(R.id.textViewHasil4);

        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doProcess();
            }
        });
    }

    private void doProcess() {
        String hasil2 = "Minat anda   :\n";
        String hasil = null;
        int startlen = hasil2.length();

        tvHasil4.setText("Asal Kota : " + spKota.getSelectedItem().toString());
        if (rpl.isChecked()) hasil2 += rpl.getText() + "\n";
        if (tkj.isChecked()) hasil2 += tkj.getText() + "\n";
        if (mlt.isChecked()) hasil2 += mlt.getText() + "\n";
        if (hasil2.length() == startlen) hasil2 += "Tidak ada pada Pilihan";
        tvHasil3.setText(hasil2);

        if (rgGender.getCheckedRadioButtonId() != -1) {
            RadioButton rb = (RadioButton)
                    findViewById(rgGender.getCheckedRadioButtonId());
            hasil = rb.getText().toString();
        }
        if (hasil == null) {
            tvHasil2.setText("Belum Memilih Gender");
        } else {
            tvHasil2.setText("Gender    : " + hasil);
        }
        if (isValid()) {
            String nama = etNama.getText().toString();
            int tahun = Integer.parseInt(etTahun.getText().toString());
            int usia = 2016 - tahun;
            tvHasil.setText("Nama Lengkap   : " + nama + " \nUsia :   " + usia + " tahun");
        }
    }

    private boolean isValid() {
        boolean valid = true;

        String nama = etNama.getText().toString();
        String tahun = etTahun.getText().toString();

        if (nama.isEmpty()) {
            etNama.setError("Nama Belum di isi!");
            valid = false;
        } else if (nama.length() < 3) {
            etNama.setError("Nama minimal 3 karakter!");
            valid = false;
        } else {
            etNama.setError(null);
        }


        if (tahun.isEmpty()) {
            etTahun.setError("Tahun Kelahiran belum diisi!");
            valid = false;
        } else if (tahun.length() != 4) {
            etTahun.setError("Format Tahun kelahiran harus yyyy");
            valid = false;
        } else {
            etTahun.setError(null);
        }
        return valid;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) nPJ += 1;
        else nPJ -= 1;
        tvPJ.setText("Peminatan Jurusan(" + nPJ + " terpilih)");
    }
}
