package com.squadtech.adminpanelquiz.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squadtech.adminpanelquiz.Models.GeneralCategoryModel;
import com.squadtech.adminpanelquiz.R;

import java.util.ArrayList;

public class GeneralQuestionAdapter extends RecyclerView.Adapter<GeneralQuestionAdapter.ViewHolderClass>
{
    private Context mContext;
    private ArrayList<GeneralCategoryModel> alistModel;

    public GeneralQuestionAdapter(Context mContext, ArrayList<GeneralCategoryModel> alistModel)
    {
        this.mContext = mContext;
        this.alistModel = alistModel;
    }

    @Override
    public ViewHolderClass onCreateViewHolder( ViewGroup parent, int viewType)
    {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.custom_questions_recyclerview, parent, false);
        return new ViewHolderClass(mView);
    }

    @Override
    public void onBindViewHolder( ViewHolderClass holder, int position)
    {
        GeneralCategoryModel generalModel = alistModel.get(position);
        holder.question.setText(generalModel.getQuestion());
        holder.opt1.setText(generalModel.getOpt1());
        holder.opt2.setText(generalModel.getOpt2());
        holder.opt3.setText(generalModel.getOpt3());
        holder.opt4.setText(generalModel.getOpt4());

    }

    @Override
    public int getItemCount() {
        return alistModel.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder
    {
        TextView question, opt1, opt2, opt3, opt4;

        public ViewHolderClass( View itemView)
        {
            super(itemView);
            question = (TextView)itemView.findViewById(R.id.retrieve_question_ID);
            opt1 = (TextView)itemView.findViewById(R.id.retrieveOption1);
            opt2 = (TextView)itemView.findViewById(R.id.retrieveOption2);
            opt3 = (TextView)itemView.findViewById(R.id.retrieveOption3);
            opt4 = (TextView)itemView.findViewById(R.id.retrieveOption4);
        }
    }
}
