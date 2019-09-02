package vn.com.lvvu.hocbanglaixemay.theory;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.com.lvvu.hocbanglaixemay.Common;
import vn.com.lvvu.hocbanglaixemay.R;
import vn.com.lvvu.hocbanglaixemay.objects.TipTrick;

/**
 * Created by levan on 7/25/2019.
 */

public class TheoryTipTrickAdapter extends RecyclerView.Adapter<TheoryTipTrickAdapter.TheoryTipTrickHolder> {

    private Context context;

    private List<TipTrick> tipTrickList;

    public TheoryTipTrickAdapter(Context context, List<TipTrick> tipTrickList) {
        this.context = context;
        this.tipTrickList = tipTrickList;
    }

    @NonNull
    @Override
    public TheoryTipTrickHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tip_trick, viewGroup, false);
        return new TheoryTipTrickHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheoryTipTrickHolder theoryTipTrickHolder, int i) {
        try {
            TipTrick tipTrick = tipTrickList.get(i);
            theoryTipTrickHolder.tvTitle.setText(tipTrick.getTitle());
            if (!Common.isTextNullOrEmpty(tipTrick.getImageDescription())) {
                theoryTipTrickHolder.ivDescription.setVisibility(View.VISIBLE);
                theoryTipTrickHolder.ivDescription.setImageURI(Uri.parse("android.resource://vn.com.lvvu.hocbanglaixemay/drawable/"
                        + tipTrick.getImageDescription()));
            } else {
                theoryTipTrickHolder.ivDescription.setVisibility(View.GONE);
                theoryTipTrickHolder.ivDescription.setImageURI(null);
            }

            if (!Common.isTextNullOrEmpty(tipTrick.getExplain1())) {
                theoryTipTrickHolder.tvExplain1.setText(tipTrick.getExplain1());
                theoryTipTrickHolder.tvExplain1.setVisibility(View.VISIBLE);
            } else {
                theoryTipTrickHolder.tvExplain1.setVisibility(View.GONE);
            }

            if (!Common.isTextNullOrEmpty(tipTrick.getExplain2())) {
                theoryTipTrickHolder.tvExplain2.setText(tipTrick.getExplain2());
                theoryTipTrickHolder.tvExplain2.setVisibility(View.VISIBLE);
            } else {
                theoryTipTrickHolder.tvExplain2.setVisibility(View.GONE);
            }

            if (!Common.isTextNullOrEmpty(tipTrick.getExplain3())) {
                theoryTipTrickHolder.tvExplain3.setText(tipTrick.getExplain3());
                theoryTipTrickHolder.tvExplain3.setVisibility(View.VISIBLE);
            } else {
                theoryTipTrickHolder.tvExplain3.setVisibility(View.GONE);
            }

            if (!Common.isTextNullOrEmpty(tipTrick.getExplain4())) {
                theoryTipTrickHolder.tvExplain4.setText(tipTrick.getExplain4());
                theoryTipTrickHolder.tvExplain4.setVisibility(View.VISIBLE);
            } else {
                theoryTipTrickHolder.tvExplain4.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public int getItemCount() {
        if (tipTrickList != null) {
            return tipTrickList.size();
        }
        return 0;
    }


    class TheoryTipTrickHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvExplain1, tvExplain2, tvExplain3, tvExplain4;
        ImageView ivDescription;

        TheoryTipTrickHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvExplain1 = itemView.findViewById(R.id.tv_explain1);
            tvExplain2 = itemView.findViewById(R.id.tv_explain2);
            tvExplain3 = itemView.findViewById(R.id.tv_explain3);
            tvExplain4 = itemView.findViewById(R.id.tv_explain4);

            ivDescription = itemView.findViewById(R.id.iv_desciption);
        }
    }

    public void setData(List<TipTrick> tipTrickList) {
        this.tipTrickList = tipTrickList;
    }

}
