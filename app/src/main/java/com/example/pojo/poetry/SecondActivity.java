package com.example.pojo.poetry;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import PullFactory.XpullFactory;
import adapter.PullAdapter;
import poetry.Author;
import poetry.Poetry;
import poetry.Type;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Poetry> list ;
    private  List<Author>  authorList;
    private  List<Type>  typeList;
    private TextView tv_all,tv_zuozhe,tv_leibie,tv_line1,tv_line2;
    private ImageButton img_search;

    private PullAdapter adapter;
    private ListView lv_poetry;
    private AlertDialog builder;
    private Button but_search;
//    private EditText et_search;
//    private String search_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);

        getIntent();
        init();
        initevent();

        lv_poetry = (ListView) findViewById(R.id.List_poems);

        XpullFactory factory = new XpullFactory();

        try {
            list = factory.getlist(getAssets().open("tangshi300.xml"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        adapter = new PullAdapter(this,list);

        lv_poetry.setAdapter(adapter);

        lv_poetry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                adapter.notifyDataSetInvalidated();
                ListView listview = (ListView) parent;
                Poetry poetry = (Poetry) listview.getItemAtPosition(position);
                Intent it = new Intent(SecondActivity.this,ThirdActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("poetry", poetry);
                it.putExtras(bundle);
                startActivity(it);


            }
        });

    }

    @Override
    public void onClick(View v) {

        setSelected(v);

        switch (v.getId()){

            case R.id.tv_all:{
                showAll();
            }
            break;
            case R.id.tv_zuozhe:{
                showAuthor();
            }
            break;
            case R.id.tv_leibie:{
                showType();
            }
            break;
            case R.id.img_search:{
                showSearch();
            }
            break;
        }

    }

    private void showSearch() {

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_search, null);
        final Dialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        dialog.getWindow().setContentView(view);

        TextView tv_key = (TextView) view.findViewById(R.id.tv_key);
        TextView tv_biaoti = (TextView) view.findViewById(R.id.tv_biaoti);
        TextView tv_shige = (TextView) view.findViewById(R.id.tv_shige);
        TextView tv_zhujie = (TextView) view.findViewById(R.id.tv_zhujie);

        RadioGroup rg_01 = (RadioGroup) view.findViewById(R.id.rg_01);
        RadioButton rb_01 = (RadioButton) view.findViewById(R.id.rb_01);
        RadioButton rb_02 = (RadioButton) view.findViewById(R.id.rb_02);
        RadioButton rb_03 = (RadioButton) view.findViewById(R.id.rb_03);

        final EditText et_search= (EditText) view.findViewById(R.id.et_search);
//        int inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL;
//        et_search.setInputType(inputType);

        but_search = (Button) view.findViewById(R.id.but_search);
        rg_01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, final int checkedId) {

                but_search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch(checkedId){
                            case R.id.rb_01:{
                                 String search_key = et_search.getText().toString();
                         //       Toast.makeText(SecondActivity.this,"标题", Toast.LENGTH_SHORT).show();
                                List<Poetry> datasByTitle=new ArrayList<>();
                                for (int i=0;i<list.size();i++){
                                    if (list.get(i).getTitle().equals(search_key)){

                                        datasByTitle.add(list.get(i));
                                    }
                                }
                                adapter.UpdateAdapter(datasByTitle);
                                dialog.dismiss();
                            }
                            break;
                            case R.id.rb_02:{
                                String search_key = et_search.getText().toString();
                                //Toast.makeText(SecondActivity.this,"shige",Toast.LENGTH_SHORT).show();
                                List<Poetry> datasByContent=new ArrayList<>();
                                for (int i=0;i<list.size();i++){
                                    if (list.get(i).getTitle().equals(search_key)){
                                        datasByContent.add(list.get(i));
                                    }
                                }
                                adapter.UpdateAdapter(datasByContent);
                                dialog.dismiss();
                            }
                            break;
                            case R.id.rb_03:{
                                String search_key = et_search.getText().toString();
                              //  Toast.makeText(SecondActivity.this,"zhujie",Toast.LENGTH_SHORT).show();
                                List<Poetry> datasByDesc=new ArrayList<>();
                                for (int i=0;i<list.size();i++){
                                    if (list.get(i).getTitle().equals(search_key)){
                                        datasByDesc.add(list.get(i));
                                    }
                                }
                                adapter.UpdateAdapter(datasByDesc);
                                dialog.dismiss();
                            }
                            break;
                        }

                    }
                });

            }
        });

    }

    private void showAll() {
        adapter = new PullAdapter(this,list);
        lv_poetry.setAdapter(adapter);
    }

    private void showType() {
        typeList = new ArrayList<Type>();
        XpullFactory  factory =new XpullFactory();
        final List<String>  typenames = new ArrayList<String>();

        try {
            typeList = factory.getlist_type(getAssets().open("tangshi300.xml"));
            for (int i =0;i <typeList.size();i++){
                if (!typenames.contains(typeList.get(i).getTypeName())) {
                    typenames.add(typeList.get(i).getTypeName());
                }
            }

            LayoutInflater inflater = LayoutInflater.from(this);
            final View dialogview = inflater.inflate(R.layout.dialog_auth, null);
            final Dialog dialog = new AlertDialog.Builder(this).create();
            dialog.show();
            dialog.getWindow().setContentView(dialogview);

            Window dialogwindow = dialog.getWindow();
            WindowManager.LayoutParams lp = dialogwindow.getAttributes();
            lp.width=900;
            lp.height =1100;
            dialogwindow.setAttributes(lp);

            final ListView lv_dialog = (ListView) dialogview.findViewById(R.id.lv_dialog);
            final ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                    typenames);
            lv_dialog.setAdapter(adapter1);

            lv_dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 //   adapter1.notifyDataSetInvalidated();
                    String search_name = typenames.get(position);
                    List<Poetry> datasByType=new ArrayList<>();
                    for (int i=0;i<list.size();i++){
                        if (list.get(i).getType().equals(search_name)){
                            datasByType.add(list.get(i));
                        }
                    }
                    adapter.UpdateAdapter(datasByType);
                    dialog.dismiss();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showAuthor() {
        authorList = new ArrayList<Author>();
        XpullFactory factory = new XpullFactory();
        final List<String> nameLists=new ArrayList<>();
        try {

            authorList = factory.getlist_auth(getAssets().open("tangshi300.xml"));


            for (int i = 0;i < authorList.size();i++){
                if (!nameLists.contains(authorList.get(i).getAuthorName())){
                    nameLists.add(authorList.get(i).getAuthorName());
                }
            }

            LayoutInflater inflater = LayoutInflater.from(this);
            final View dialogview = inflater.inflate(R.layout.dialog_auth, null);
            final Dialog dialog  = new AlertDialog.Builder(this).create();
            dialog.show();
            dialog.getWindow().setContentView(dialogview);

            Window dialogwindow = dialog.getWindow();
            WindowManager.LayoutParams lp = dialogwindow.getAttributes();
            lp.width=900;
            lp.height = 1800;
            dialogwindow.setAttributes(lp);

            final ListView lv_dialog = (ListView) dialogview.findViewById(R.id.lv_dialog);
            final ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                    nameLists);
            lv_dialog.setAdapter(adapter1);

            lv_dialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //    adapter1.notifyDataSetInvalidated();
                    String search_name = nameLists.get(position);
                    List<Poetry> datasByauthor=new ArrayList<>();
                    for (int i=0;i<list.size();i++){
                        if (list.get(i).getAuth().equals(search_name)){
                            datasByauthor.add(list.get(i));
                        }
                    }
                    adapter.UpdateAdapter(datasByauthor);
                    dialog.dismiss();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setSelected(View v) {
        tv_all.setSelected(v.getId()==R.id.tv_all);
        tv_zuozhe.setSelected(v.getId()==R.id.tv_zuozhe);
        tv_leibie.setSelected(v.getId()==R.id.tv_leibie);
    }

    private void initevent() {
        tv_all.setOnClickListener(this);
        tv_zuozhe.setOnClickListener(this);
        tv_leibie.setOnClickListener(this);
        img_search.setOnClickListener(this);
    }
    private void init() {
        tv_all = (TextView) findViewById(R.id.tv_all);
        tv_zuozhe = (TextView) findViewById(R.id.tv_zuozhe);
        tv_leibie = (TextView) findViewById(R.id.tv_leibie);
        tv_line1 = (TextView) findViewById(R.id.tv_line1);
        tv_line2 = (TextView) findViewById(R.id.tv_line2);
        img_search = (ImageButton) findViewById(R.id.img_search);
    }
}
