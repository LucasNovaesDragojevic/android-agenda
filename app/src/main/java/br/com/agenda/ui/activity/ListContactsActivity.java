package br.com.agenda.ui.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.agenda.R;

public class ListContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Contacts");
        setContentView(R.layout.activity_list_contacts);
        List<String> alunos = new ArrayList<>(Arrays.asList("Alex", "Fran", "Jose", "Tahiana"));
        ListView listaAlunos = findViewById(R.id.activity_list_contacts_listview);
        listaAlunos.setAdapter(
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        alunos));
    }
}
