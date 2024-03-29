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
import com.example.budget.adapter.LoaiChiSpinnerAdapter;
import com.example.budget.entity.Chi;
import com.example.budget.entity.LoaiChi;
import com.example.budget.ui.chi.KhoanChiFragment;

import com.example.budget.ui.chi.KhoanChiViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ChiDialog {
    private KhoanChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etId, etName, etAmount, etNote;
    private Spinner spType;
    private boolean mEditMode;

    private LoaiChiSpinnerAdapter mAdapter;

    public ChiDialog(Context context, KhoanChiFragment fragment, Chi... chi) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_chi, null);
        etId = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
        etAmount = view.findViewById(R.id.etAmount);
        etNote = view.findViewById(R.id.etNote);
        spType = view.findViewById(R.id.spType);
        mAdapter = new LoaiChiSpinnerAdapter(fragment.getActivity());
        mViewModel.getAllLoaiChi().observe(fragment.getActivity(), new Observer<List<LoaiChi>>() {
            @Override
            public void onChanged(List<LoaiChi> loaiChis) {
                mAdapter.setList(loaiChis);
            }
        });
        spType.setAdapter(mAdapter);
        if(chi != null && chi.length>0) {
            etId.setText(""+ chi[0].tid);
            etName.setText(chi[0].ten);
            etAmount.setText(""+ chi[0].sotien);
            etNote.setText(chi[0].ghichu);
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
                Chi lt = new Chi();
                lt.ten = etName.getText().toString();
                lt.sotien = Float.parseFloat(etAmount.getText().toString());
                lt.ghichu = etNote.getText().toString();
                lt.lcid = ((LoaiChi) mAdapter.getItem(spType.getSelectedItemPosition())).lid;
                if(mEditMode) {
                    lt.tid = Integer.parseInt(etId.getText().toString());
                    // 9.1 gọi tới update
                    mViewModel.update(lt);
                }else {
                    // 6.1 gọi tới insert
                    mViewModel.insert(lt);
                    // 7. hiện thị thông báo luu
                    Toast.makeText(context, "Khoan chi được lưu", Toast.LENGTH_SHORT).show();
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
