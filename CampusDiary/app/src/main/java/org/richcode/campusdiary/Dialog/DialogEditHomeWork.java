package org.richcode.campusdiary.Dialog;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import org.richcode.campusdiary.DataBase.DBHelper;
import org.richcode.campusdiary.R;

import java.util.Calendar;

public class DialogEditHomeWork extends Dialog implements View.OnClickListener {
    private MyDialogListener dialogListener;
    private Context context;

    Button DayPickerButton;
    Button CancelButton;
    Button OKButton;
    TextView DayText;
    EditText EditClassName;
    EditText EditContent;

    String Date;
    String Content;
    String Subject;

    DBHelper dbHelper;


    public DialogEditHomeWork(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public void setDialogListener(MyDialogListener dialogListener){
        this.dialogListener = dialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edithomework);

        dbHelper = new DBHelper(getContext(),"DataBase.db",null,2);

        DayPickerButton = (Button)findViewById(R.id.button_datepicker);
        CancelButton = (Button)findViewById(R.id.button_edithomework_cancel);
        OKButton = (Button)findViewById(R.id.button_edithomework_complete);
        DayText = (TextView)findViewById(R.id.text_daypicker);
        EditClassName = (EditText)findViewById(R.id.edit_homework_classname);
        EditContent = (EditText)findViewById(R.id.edit_homework_content);

        DayPickerButton.setOnClickListener(this);
        CancelButton.setOnClickListener(this);
        OKButton.setOnClickListener(this);
        DayText.setText("날짜를 선택해주세요");
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.button_datepicker:
                showDialog();
                break;
            case R.id.button_edithomework_cancel:
                dismiss();
                break;
            case R.id.button_edithomework_complete:
                Subject = EditClassName.getText().toString();
                Content = EditContent.getText().toString();
                //D_day
                dbHelper.HomeworkInsert(Date,Subject,Content);
                dialogListener.onNegativeClicked();
                dismiss();
                break;
        }


    }

    private void showDialog(){

        final Calendar pickedDate = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month +=1;
                        Date = year + "." + month + "." + dayOfMonth;

                        DayText.setText(Date);
                    }
                },
                pickedDate.get(Calendar.YEAR),
                pickedDate.get(Calendar.MONTH),
                pickedDate.get(Calendar.DATE)
        );

        datePickerDialog.show();
    }

}
