package com.example.orderfoodapp.Adaptor;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.orderfoodapp.Domain.FoodDomain;
import com.example.orderfoodapp.R;

import java.util.ArrayList;

public class PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.ViewHolder> {
    ArrayList<FoodDomain> popularFood;

    public PopularAdaptor(ArrayList<FoodDomain> popularFood) {
        this.popularFood = popularFood;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View infalse = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
        return new ViewHolder(infalse);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Lấy đối tượng FoodDomain tại vị trí
        FoodDomain food = popularFood.get(position);

        // Thiết lập tên món ăn và giá
        holder.titlepopular.setText(food.getTitle());
        holder.fee.setText(String.valueOf(food.getFee()));

        String imagePath = food.getPicpopular(); // Lấy đường dẫn hình ảnh

        // Kiểm tra xem imagePath có phải URL hoặc là tên tài nguyên drawable không
        if (imagePath != null) {
            if (imagePath.startsWith("http") || imagePath.startsWith("https")) {
                // Nếu là URL, sử dụng Glide để tải hình ảnh
                Glide.with(holder.itemView.getContext())
                        .load(imagePath)
                        .placeholder(R.drawable.nguoinauan)  // Ảnh thay thế khi đang tải
                        .error(R.drawable.nguoinauan)              // Ảnh thay thế khi lỗi tải
                        .into(holder.picpopular);
            } else {
                // Nếu là tài nguyên drawable, lấy resource ID
                int drawableResourceId = holder.itemView.getContext().getResources()
                        .getIdentifier(imagePath, "drawable", holder.itemView.getContext().getPackageName());

                // Kiểm tra tài nguyên có hợp lệ không
                if (drawableResourceId != 0) {
                    holder.picpopular.setImageResource(drawableResourceId);
                } else {
                    // Nếu không tìm thấy tài nguyên, dùng ảnh mặc định
                    holder.picpopular.setImageResource(R.drawable.nguoinauan);
                }
            }
        } else {
            // Nếu không có đường dẫn hình ảnh, sử dụng ảnh mặc định
            holder.picpopular.setImageResource(R.drawable.nguoinauan);
        }
    }

    @Override
    public int getItemCount() {
        return popularFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titlepopular, fee;
        ImageView picpopular;
        TextView addBtn;

        @SuppressLint("WrongViewCast")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titlepopular = itemView.findViewById(R.id.titlepopular);
            fee = itemView.findViewById(R.id.fee);
            picpopular = itemView.findViewById(R.id.picpopular);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}
