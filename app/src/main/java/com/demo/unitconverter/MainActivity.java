package com.demo.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private String[] starArray = {"Metre", "Celsius", "Kilograms"};
    private int type;
    private EditText editText;
    private TextView text_uni1, text_val1, text_uni2, text_val2, text_uni3, text_val3;

    private String[] titles_metre = {"Centimetre", "Foot", "Inch"};
    private String[] titles_celsius = {"Fahrenheit", "Kelvin", ""};
    private String[] titles_kg = {"Gram", "Ounce(Oz)", "Pound(Ib)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSpinner();

        initImageView();

        initEditView();

        initTextView();

        setUnit(titles_metre);
    }

    private void setUnit(String[] arr) {

        initVal();

        text_uni1.setText(arr[0]);
        text_uni2.setText(arr[1]);
        text_uni3.setText(arr[2]);
    }

    private void initVal() {

        text_val1.setText("");
        text_val2.setText("");
        text_val3.setText("");
    }

    private void initTextView() {

        text_uni1 = findViewById(R.id.text_uni1);
        text_uni2 = findViewById(R.id.text_uni2);
        text_uni3 = findViewById(R.id.text_uni3);

        text_val1 = findViewById(R.id.text_val1);
        text_val2 = findViewById(R.id.text_val2);
        text_val3 = findViewById(R.id.text_val3);

    }

    private void initEditView() {

        editText = findViewById(R.id.editText);

/*
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Toast.makeText(MainActivity.this, "您输入的是：" + charSequence.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
*/


    }

    private void initImageView() {

        findViewById(R.id.iv_metre).setOnClickListener(view -> startConvert(0));
        findViewById(R.id.iv_temp).setOnClickListener(view -> startConvert(1));
        findViewById(R.id.iv_kg).setOnClickListener(view -> startConvert(2));

    }

    private void startConvert(int position) {

        if (position != type) {

            Toast.makeText(MainActivity.this, "Please select the correct conversion icon!", Toast.LENGTH_SHORT).show();
            return;
        }
        try {

            int value = Integer.parseInt(editText.getText().toString().trim());

            if (type==0){

                convert1(value);

            }else if (type==1){

                convert2(value);
            }else {

                convert3(value);
            }

        } catch (Exception e) {
            e.printStackTrace();

            Toast.makeText(MainActivity.this, "Please enter the correct number!", Toast.LENGTH_SHORT).show();
        }

    }

    private void convert1(int value){

        DecimalFormat df = new DecimalFormat("#.00");

        text_val1.setText((value * 100)+"");
        text_val2.setText(df.format(value * 3.28)+"");
        text_val3.setText(df.format((1/2.54 * value * 100))+"");

    }

    private void convert2(int value){

        DecimalFormat df = new DecimalFormat("#.00");

        text_val1.setText(df.format(1.8 * value + 32)+"");
        text_val2.setText(df.format(value + 273.15)+"");

    }

    private void convert3(int value){

        DecimalFormat df = new DecimalFormat("#.00");

        text_val1.setText((1000 * value)+"");
        text_val2.setText(df.format(value * 35.27396194958)+"");
        text_val3.setText(df.format(value * 2.2046)+"");

    }


    private void initSpinner() {

        //声明一个下拉列表的数组适配器
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, starArray);
        //设置数组适配器的布局样式
//        starAdapter.setDropDownViewResource(R.layout.item_dropdown);
        //从布局文件中获取名叫sp_dialog的下拉框
        Spinner sp = findViewById(R.id.spinner);
        //设置下拉框的标题，不设置就没有难看的标题了
//        sp.setPrompt("请选择行星");
        //设置下拉框的数组适配器
        sp.setAdapter(starAdapter);
        //设置下拉框默认的显示第一项
        sp.setSelection(0);
        //给下拉框设置选择监听器，一旦用户选中某一项，就触发监听器的onItemSelected方法
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                type = i;

                if (i == 0) {
                    setUnit(titles_metre);
                } else if (i == 1) {
                    setUnit(titles_celsius);
                } else {
                    setUnit(titles_kg);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


}