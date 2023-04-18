package com.example.budget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.budget.R;
import com.example.budget.entity.LoaiThu;
import com.example.budget.ui.thu.LoaiThuFragment;
import com.example.budget.ui.thu.LoaiThuViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class LoaiThuDialog {
    private LoaiThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etId, etName;
    private boolean mEditMode;

    public LoaiThuDialog(Context context, LoaiThuFragment fragment) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_loai_thu, null);
        etId = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setView(view).setNegativeButton("Dong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDialog.dismiss();
            }
        }).setPositiveButton("Luu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoaiThu lt = new LoaiThu();
                lt.ten = etName.getText().toString();
                mViewModel.insert(lt);
                Toast.makeText(context, "Loai thu duoc luu", Toast.LENGTH_SHORT).show();
            }
        });
        mDialog = builder.create();
    }
    public void show() {
        mDialog.show();
    }
}
