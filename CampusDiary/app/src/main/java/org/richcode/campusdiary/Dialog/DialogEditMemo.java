package org.richcode.campusdiary.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.richcode.campusdiary.DataBase.DBHelper;
import org.richcode.campusdiary.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogEditMemo extends Dialog implements View.OnClickListener {
    private MyDialogListener dialogListener;
    private Context context;

    Button OkButton;
    Button CancelButton;
    EditText EditMemo;

    DBHelper dbHelper;

    String date;

    public DialogEditMemo(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public void setDialogListener(MyDialogListener dialogListener){
        this.dialogListener = dialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_editmemo);

        dbHelper = new DBHelper(getContext(),"DataBase.db",null,2);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date CurrentTIme = new Date();
        date = simpleDateFormat.format(CurrentTIme);

        EditMemo = (EditText)findViewById(R.id.edit_memo_content);
        OkButton = (Button)findViewById(R.id.button_editmemo_complete);
        CancelButton = (Button)findViewById(R.id.button_editmemo_cancel);
        OkButton.setOnClickListener(this);
        CancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.button_editmemo_cancel:
                dismiss();
                break;
            case R.id.button_editmemo_complete:
                dialogListener.onNegativeClicked();
                dbHelper.MemoInsert(date,EditMemo.getText().toString());
                dismiss();
                break;
        }
    }

}
