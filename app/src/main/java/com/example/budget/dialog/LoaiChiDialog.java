package com.example.budget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.budget.R;
import com.example.budget.entity.LoaiChi;
import com.example.budget.entity.LoaiThu;
import com.example.budget.ui.chi.LoaiChiFragment;
import com.example.budget.ui.chi.LoaiChiViewModel;
import com.example.budget.ui.thu.LoaiThuFragment;
import com.example.budget.ui.thu.LoaiThuViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class LoaiChiDialog {
    private LoaiChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etId, etName;
    private boolean mEditMode;

    public LoaiChiDialog(Context context, LoaiChiFragment fragment, LoaiChi ...loaiChi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_loai_chi, null);
        etId = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
        if(loaiChi != null && loaiChi.length>0) {
            etId.setText(""+loaiChi[0].lid);
            etName.setText(loaiChi[0].ten);
            mEditMode = true;
        }else {
            mEditMode = false;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setView(view).setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDialog.dismiss();
            }
        }).setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoaiChi lc = new LoaiChi();
                lc.ten = etName.getText().toString();
                if(mEditMode) {
                    lc.lid = Integer.parseInt(etId.getText().toString());
                    mViewModel.update(lc);
                }else {
                    mViewModel.insert(lc);
                    Toast.makeText(context, "Loại chi được lưu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mDialog = builder.create();
    }
    public void show() {
        mDialog.show();
    }
}
