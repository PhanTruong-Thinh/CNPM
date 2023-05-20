package com.example.budget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.example.budget.R;
import com.example.budget.adapter.LoaiThuSpinnerAdapter;
import com.example.budget.entity.Thu;
import com.example.budget.entity.LoaiThu;
import com.example.budget.ui.thu.KhoanThuFragment;

import com.example.budget.ui.thu.KhoanThuViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ThuDialog {
    private KhoanThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etId, etName, etAmount, etNote;
    private Spinner spType;
    private boolean mEditMode;

    private LoaiThuSpinnerAdapter mAdapter;

    public ThuDialog(Context context, KhoanThuFragment fragment, Thu... thu) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_thu, null);
        etId = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
        etAmount = view.findViewById(R.id.etAmount);
        etNote = view.findViewById(R.id.etNote);
        spType = view.findViewById(R.id.spType);
        mAdapter = new LoaiThuSpinnerAdapter(fragment.getActivity());
        mViewModel.getAllLoaiThu().observe(fragment.getActivity(), new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaiThus) {
                mAdapter.setList(loaiThus);
            }
        });
        spType.setAdapter(mAdapter);
        if(thu != null && thu.length>0) {
            etId.setText(""+ thu[0].tid);
            etName.setText(thu[0].ten);
            etAmount.setText(""+ thu[0].sotien);
            etNote.setText(thu[0].ghichu);
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
                Thu lt = new Thu();
                lt.ten = etName.getText().toString();
                lt.sotien = Float.parseFloat(etAmount.getText().toString());
                lt.ghichu = etNote.getText().toString();
                lt.ltid = ((LoaiThu) mAdapter.getItem(spType.getSelectedItemPosition())).lid;
                if(mEditMode) {
                    lt.tid = Integer.parseInt(etId.getText().toString());
                    // 9.1 gọi tới update
                    mViewModel.update(lt);
                }else {
                    // 6.1 gọi tới insert
                    mViewModel.insert(lt);
                    // 7. hiện thị thông báo luu
                    Toast.makeText(context, "Khoan thu được lưu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mDialog = builder.create();
    }
    // 5.2 Hiển thị hộp thoại người dùng nhập thông tin
    // 8.2
    public void show() {
        mDialog.show();
    }
}
