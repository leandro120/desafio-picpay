package com.picpay.desafio.android

import android.view.View
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import org.hamcrest.Matcher


class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
    val name = KTextView(parent) { withId(R.id.name) }
    val username = KTextView(parent) { withId(R.id.username) }
}

class MainScreen : Screen<MainScreen> (){
    val recycler: KRecyclerView = KRecyclerView({
        withId(R.id.recyclerView)
    }, itemTypeBuilder = {
        itemType(::Item)
    })
}