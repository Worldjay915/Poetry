package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pojo.poetry.R;

import java.util.List;

import poetry.Author;

/**
 * Created by zhangshijie on 2016/7/28.
 */
public class AuthDialogAdapter extends BaseAdapter {

    Context context;
    List<Author> list ;
    LayoutInflater inflater;
    int  selectedPosition =-1;

    public AuthDialogAdapter(Context context, List<Author> list){
        this.context = context ;
        this.list = list;
        inflater = LayoutInflater.from(context);
        //

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

    public void UpdateAdapter(  List<Author> list ){

           this.list=list;
           notifyDataSetChanged();
    }
    @Override
    public View getView(int position , View view, ViewGroup viewGroup) {

        Author author = (Author) getItem(position);
        ViewHolder viewHolder ;
        View v;
        if (view ==null){
            v = inflater.inflate(R.layout.item_dialog, null);
            viewHolder = new ViewHolder();
            viewHolder.Name = (TextView) v.findViewById(R.id.tv_dialog);
            v.setTag(viewHolder);

        }else{
            v = view;
            viewHolder = (ViewHolder) v.getTag();
        }


       /* if (selectedPosition == position) {

            v.setBackgroundResource(R.drawable.listview_color);
        } else {

            v.setBackgroundResource(R.drawable.listview_color);
        }*/

        viewHolder.Name.setText(author.getAuthorName());





        return v;
    }


    class ViewHolder{

        TextView Name;

    }
}
