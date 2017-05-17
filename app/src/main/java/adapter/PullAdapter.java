package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pojo.poetry.R;

import java.util.List;

import poetry.Poetry;

/**
 * Created by zhangshijie on 2016/7/28.
 */
public class PullAdapter extends BaseAdapter {

    Context context;
    List<Poetry> list ;
    LayoutInflater inflater;
    int  selectedPosition =-1;

    public PullAdapter(Context context,List<Poetry> list){
        this.context = context ;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void UpdateAdapter(  List<Poetry> list ){

           this.list=list;
           notifyDataSetChanged();
    }
    @Override
    public View getView(int position , View view, ViewGroup viewGroup) {

        Poetry poetry = (Poetry) getItem(position);

        ViewHolder viewHolder ;
        View v;
        if (view ==null){
            v = inflater.inflate(R.layout.item_main, null);
            viewHolder = new ViewHolder();

            viewHolder.firstName = (TextView) v.findViewById(R.id.tv_first);
            viewHolder.Heng = (TextView) v.findViewById(R.id.tv_heng);
            viewHolder.authName = (TextView) v.findViewById(R.id.tv_author);
            viewHolder.typeName = (TextView) v.findViewById(R.id.tv_leibie);
  //          viewHolder.more= (TextView) v.findViewById(R.id.tv_more);

            v.setTag(viewHolder);

        }else{
            v = view;
            viewHolder = (ViewHolder) v.getTag();


        }

        if (selectedPosition == position) {

            v.setBackgroundResource(R.drawable.listview_color);
        } else {

            v.setBackgroundResource(R.drawable.listview_color);
        }
        viewHolder.firstName.setText(poetry.getTitle());
        viewHolder.Heng.setText("--");
        viewHolder.authName.setText(poetry.getAuth());
        viewHolder.typeName.setText(poetry.getType());



        return v;
    }


    class ViewHolder{

        TextView firstName;
        TextView Heng;
        TextView authName;
        TextView typeName;
        TextView more;

    }
}
