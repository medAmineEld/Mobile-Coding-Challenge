package challenge.coding.mobile.mobile_coding_challenge.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import challenge.coding.mobile.mobile_coding_challenge.R;
import challenge.coding.mobile.mobile_coding_challenge.model.MostStarredRepo;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterRepo extends RecyclerView.Adapter<AdapterRepo.MyViewHolder>{

    private Context mContext;
    private List<MostStarredRepo> repos;
    AppCompatActivity act;
    public AdapterRepo(AppCompatActivity act,Context mContext,List<MostStarredRepo> repos)
    {
        this.mContext = mContext;
        this.repos = repos;
        this.act=act;
    }

    @Override
    public AdapterRepo.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_repo,viewGroup,false);
        return new AdapterRepo.MyViewHolder(view);
    }

    @Override
    public  void onBindViewHolder(final AdapterRepo.MyViewHolder viewHolder, int i)
    {
        /*viewHolder.text.setText(movieList.get(i).getText());

        viewHolder.user_name.setText(movieList.get(i).getFname());

        Glide.with(mContext).load(movieList.get(i).getphoto()).placeholder(R.drawable.load)
                .into(viewHolder.icon_user);

        if(currentUser!=null && currentUser.getPhotoUrl().toString().equals(movieList.get(i).getphoto()))
        {
            viewHolder.icon_trash.setVisibility(View.VISIBLE);
        }*/
    }
    @Override
    public int getItemCount()
    {
        return repos.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder
    {
       public TextView user_name,text;
        public ImageView icon_trash;
        public CircleImageView icon_user;
        public MyViewHolder(View view)
        {
            super(view);
             /*text = (TextView) view.findViewById(R.id.text);
            user_name = (TextView) view.findViewById(R.id.user_name);
            icon_user = view.findViewById(R.id.icon_user);
            icon_trash=(ImageView)view.findViewById(R.id.icon_trash);
            icon_trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION)
                    {
                        FirebaseDatabase.getInstance().getReference().child(path+lst_id_cmt.get(pos)+"/").removeValue();
                        lst_id_cmt.remove(pos);
                        movieList.remove(pos);
                        CommentAdapter.this.notifyDataSetChanged();
                        ExplosionField mExplosionField  = ExplosionField.attach2Window(act);
                        mExplosionField.explode(view);
                    }

                }
            });*/
        }
    }


}


