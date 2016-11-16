package com.gihub.leonardoxh.lenny

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_faces.view.*

class FacesAdapter(val faces: List<String>, val faceClickListener: OnFaceClick) : RecyclerView.Adapter<FacesAdapter.FacesViewHolder>() {

    override fun onBindViewHolder(holder: FacesViewHolder?, position: Int) {
        holder?.bindTo(faces[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FacesViewHolder {
        return FacesViewHolder(LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_faces, parent, false))
    }

    override fun getItemCount(): Int {
        return faces.size
    }

    inner class FacesViewHolder : RecyclerView.ViewHolder {

        constructor(itemView: View?) : super(itemView) {
            itemView?.setOnClickListener {
                faceClickListener.onFaceClick(faces[layoutPosition])
            }
        }

        fun bindTo(face: String) {
            itemView.faceItem.text = face
        }

    }

    interface OnFaceClick {
        fun onFaceClick(face: String)
    }

}