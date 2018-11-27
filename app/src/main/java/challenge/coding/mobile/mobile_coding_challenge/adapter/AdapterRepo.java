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
import challenge.coding.mobile.mobile_coding_challenge.model.Repository;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterRepo extends RecyclerView.Adapter<AdapterRepo.MyViewHolder>{

    private Context mContext;
    private List<Repository> repos;
    public AdapterRepo(Context mContext,List<Repository> repos)
    {
        this.mContext = mContext;
        this.repos = repos;
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
        viewHolder.repo_name.setText(repos.get(i).getFull_name());
        viewHolder.repo_desc.setText(repos.get(i).getDescription());
        viewHolder.owner_name.setText(repos.get(i).getOwner().getLogin());
        viewHolder.repo_stars.setText(withSuffix(repos.get(i).getStargazers_count()));

        Glide.with(mContext).load(repos.get(i).getOwner().getAvatar_url()).placeholder(R.drawable.load)
                .into(viewHolder.avatar);

    }
    public static String withSuffix(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f %c",
                count / Math.pow(1000, exp),
                "kMGTPE".charAt(exp-1));
    }
    @Override
    public int getItemCount()
    {
        return repos.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder
    {
       public TextView repo_name,repo_desc,owner_name,repo_stars;
        public CircleImageView avatar;
        public MyViewHolder(View view)
        {
            super(view);
            repo_name =  view.findViewById(R.id.repo_name);
            repo_desc = view.findViewById(R.id.repo_desc);
            owner_name = view.findViewById(R.id.owner_name);
            repo_stars =  view.findViewById(R.id.repo_stars);
            avatar =  view.findViewById(R.id.avatar);
        }
    }


}


