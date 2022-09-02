package com.agena.android.weatherforecast.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.agena.android.weatherforecast.databinding.CityWeatherItemBinding
import com.agena.android.weatherforecast.model.WeatherEntity
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class CityWeatherAdapter(
    val context: Context
) : RecyclerView.Adapter<CityWeatherAdapter.ResultItemViewHolder>() {

    private var items = listOf<WeatherEntity>()

    fun addList(list: List<WeatherEntity>) {
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultItemViewHolder {
        val binding = CityWeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultItemViewHolder, position: Int) {
        holder.binding.cityName.text = items[position].cityName
        holder.binding.localTime.text = ZonedDateTime.now(
            ZoneOffset.ofTotalSeconds(items[position].timezone)
        ).format(DateTimeFormatter.ofPattern("HH:mm"))
        holder.binding.temperature.text = items[position]
            .temperature
            .roundToInt()
            .toString()
            .plus("°C")
        holder.binding.weatherDescription.text = items[position].weatherDescription

        val value = items[position].iconId
        val resourceId: Int = context.resources.getIdentifier(
            "ic_$value",
            "drawable",
            context.packageName
        )

        if (resourceId != 0) {
            holder.binding.weatherIcon.setImageDrawable(
                ContextCompat.getDrawable(context, resourceId)
            )
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ResultItemViewHolder(val binding: CityWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
