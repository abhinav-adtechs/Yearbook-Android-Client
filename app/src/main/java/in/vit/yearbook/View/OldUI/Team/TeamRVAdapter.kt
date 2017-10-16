package `in`.vit.yearbook.View.OldUI.Team


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import `in`.vit.yearbook.Model.Utils.Constants
import `in`.vit.yearbook.R


class TeamRVAdapter(private val teamList: Array<Array<String>>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View

        if (viewType == Constants.LIST_TYPE_HEADER) {
            itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_team_list_header, parent, false)
            return TeamViewHolderHeader(itemView)
        } else {
            itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_team_list_member, parent, false)
            return TeamViewHolderMember(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val type = getItemViewType(position)
        if (type == Constants.LIST_TYPE_HEADER) {
            val holderHeader = holder as TeamViewHolderHeader
            holderHeader.tvTitle.text = teamList[position][0]
            when (teamList[position][0]) {
                "Editorial" -> holderHeader.ivDescription.setImageDrawable(context.resources.getDrawable(R.drawable.ic_notepad_2))
                "Design" -> holderHeader.ivDescription.setImageDrawable(context.resources.getDrawable(R.drawable.ic_layers_2))
                "Photography" -> holderHeader.ivDescription.setImageDrawable(context.resources.getDrawable(R.drawable.ic_photo_camera))
                "Management" -> holderHeader.ivDescription.setImageDrawable(context.resources.getDrawable(R.drawable.ic_customer_service))
            }

        } else {
            val holderMember = holder as TeamViewHolderMember
            holderMember.tvMemberName.text = teamList[position][0]
        }
    }


    override fun getItemCount(): Int {
        return teamList.size
    }

    inner class TeamViewHolderHeader(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTitle: TextView
        val ivDescription: ImageView

        init {

            tvTitle = itemView.findViewById(R.id.item_team_list_header_title) as TextView
            ivDescription = itemView.findViewById(R.id.item_team_list_header_iv) as ImageView
        }
    }

    inner class TeamViewHolderMember(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvMemberName: TextView

        init {

            tvMemberName = itemView.findViewById(R.id.item_team_list_member_tv) as TextView

        }
    }


    override fun getItemViewType(position: Int): Int {
        return Integer.parseInt(teamList[position][1])
    }

}
