package in.vit.yearbook.View.Dashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import in.vit.yearbook.Model.UIModels.DashboardBook;
import in.vit.yearbook.R;


public class DashboardBooksAdapter extends RecyclerView.Adapter<DashboardBooksAdapter.BookViewHolder> {

    private List<DashboardBook> dashboardBooks ;
    private Context context ;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void OnItemClick(int position) ;
    }

    public DashboardBooksAdapter(List<DashboardBook> dashboardBooks, Context context, OnItemClickListener onItemClickListener) {
        this.dashboardBooks = dashboardBooks;
        this.context = context ;
        this.onItemClickListener = onItemClickListener ;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_books_rv, parent, false) ;
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {

        holder.bookTitle.setText(dashboardBooks.get(position).getBookTitle());
        holder.ivBookThumbnail.setBackground(context.getResources().getDrawable(bookThumbnails[position]));
        holder.bindListener(position);
    }

    @Override
    public int getItemCount() {
        return dashboardBooks.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{

        private TextView bookTitle ;
        private ImageView ivBookThumbnail ;

        public BookViewHolder(View itemView) {
            super(itemView);

            bookTitle = (TextView) itemView.findViewById(R.id.item_books_rv_title) ;
            ivBookThumbnail = (ImageView) itemView.findViewById(R.id.item_books_rv_thumbnail) ;

        }

        public void bindListener(final int position){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.OnItemClick(position);
                }
            });
        }
    }


    private int[] bookThumbnails = new int[]{
            R.drawable.yb2017_cover,
            R.drawable.yb2017_cover,
            R.drawable.yb2017_cover,
            R.drawable.yb2017_cover,
            R.drawable.yb2017_cover
    };
}
