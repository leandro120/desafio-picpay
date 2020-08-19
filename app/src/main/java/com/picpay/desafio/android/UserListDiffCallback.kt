package com.picpay.desafio.android

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.domain.entity.User

class UserListDiffCallback(
    private val oldList: List<User>,
    private val newList: List<User>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize()= oldList.size

    override fun getNewListSize()= newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int)=
        oldList[oldItemPosition] == newList[newItemPosition]

}