package com.team9.admin.turtle001.Fragment_main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.taobao.library.BaseBannerAdapter;
import com.taobao.library.VerticalBannerView;
import com.team9.admin.turtle001.R;
import java.util.List;

class TBAdapter extends BaseBannerAdapter<Model> {

        private Context mcontext;

    public TBAdapter(List<Model> datas, Context context) {
        super(datas);

        this.mcontext = context;
        }

    @Override
    public View getView(VerticalBannerView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_roll,null);
        }

    @Override
    public void setItem(final View view, final Model model) {
        //给控件赋值
        TextView title = view.findViewById(R.id.tv);
        title.setText(model.title);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                intent =new Intent(mcontext.getApplicationContext(),WebActivity.class);
                intent.putExtra("url",model.url);
                mcontext.startActivity(intent);
            }
        });
    }

}
