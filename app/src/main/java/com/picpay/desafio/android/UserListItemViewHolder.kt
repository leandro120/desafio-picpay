package com.picpay.desafio.android

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.picpay.desafio.android.domain.entity.User
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(
    private var context: Context,
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {
        itemView.name.text = user.name
        itemView.username.text = user.username
        itemView.progressBar.visibility = View.VISIBLE
        Glide.with(context)
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .apply(
                RequestOptions()
                    .placeholder(R.color.colorPrimary)
                    .dontAnimate().skipMemoryCache(true)
            )
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    itemView.progressBar.visibility = View.GONE
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    itemView.progressBar.visibility = View.GONE
                    return false
                }
            })
            .into(itemView.picture)
    }
}