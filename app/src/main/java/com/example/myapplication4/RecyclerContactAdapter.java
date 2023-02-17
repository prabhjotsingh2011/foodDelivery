package com.example.myapplication4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.viewHolder> {
    Context context;
    ArrayList<ContactModel> arrContact;
    RecyclerContactAdapter(Context context,ArrayList<ContactModel> arrContact){
        this.context=context;
        this.arrContact=arrContact;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        viewHolder viewHolder=new viewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.imgContect.setImageResource(arrContact.get(position).img);
        holder.txtnumber.setText(arrContact.get(position).number);
        holder.txtname.setText(arrContact.get(position).name);

        setAnimation(holder.itemView,position);

        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_update_lay);

                EditText edtName = dialog.findViewById(R.id.edtName);
                TextView txtTitle = dialog.findViewById(R.id.txtTitle);
                EditText edtNumber = dialog.findViewById(R.id.edtNumber);
                Button btnAction= dialog.findViewById(R.id.edtAction);

                btnAction.setText("Update");
                txtTitle.setText("UPDATE CONTACT");
                edtName.setText((arrContact.get(position)).name);
                edtNumber.setText((arrContact.get(position)).number);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name="",number="";
                        if(!edtName.getText().toString().equals("") && !edtNumber.getText().toString().equals("")){
                            name= edtName.getText().toString();
                            number=edtNumber.getText().toString();
                        }else {
                            Toast.makeText(context, "Please enter all the details ", Toast.LENGTH_SHORT).show();
                        }
                        arrContact.set(position,new ContactModel(name,number));
                        notifyItemChanged(position,arrContact.size()-1);

                        dialog.dismiss();
                    }
                });

                dialog.show();
                Window window= dialog.getWindow();
                window.setLayout(1000,1100);
            }
        });

        holder.llRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure you want to delete ?")
                        .setIcon(R.drawable.baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                arrContact.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrContact.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView txtname,txtnumber;
        ImageView imgContect;
        LinearLayout llRow;
        public viewHolder(View itemView){
            super(itemView);
            txtname=itemView.findViewById(R.id.txtName);
            txtnumber=itemView.findViewById(R.id.txtNumber);
            imgContect=itemView.findViewById(R.id.imgContact);
            llRow=itemView.findViewById(R.id.llRow);
        }

    }

    private void setAnimation(View viewToAnimate, int  position){
        Animation slideIn = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewToAnimate.startAnimation(slideIn);
    }
}
