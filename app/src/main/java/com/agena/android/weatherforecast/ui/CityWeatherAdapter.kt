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
    private val context: Context,
    private val onItemClicked: (weatherEntity: WeatherEntity) -> Unit
) : RecyclerView.Adapter<CityWeatherAdapter.ResultItemViewHolder>() {

    private var items = listOf<WeatherEntity>()

    fun addList(list: List<WeatherEntity>) {
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultItemViewHolder {
        val binding = CityWeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultItemViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: ResultItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ResultItemViewHolder(
        private val binding: CityWeatherItemBinding,
        val onItemClicked: (weatherEntity: WeatherEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(weatherEntity: WeatherEntity) {
            with(binding) {
                cityName.text = weatherEntity.cityName
                localTime.text = ZonedDateTime.now(
                    ZoneOffset.ofTotalSeconds(weatherEntity.timezone)
                ).format(DateTimeFormatter.ofPattern("HH:mm"))
                temperature.text = weatherEntity
                    .temperature
                    .roundToInt()
                    .toString()
                    .plus("°C")
                weatherDescription.text = weatherEntity.weatherDescription

                val value = weatherEntity.iconId
                val resourceId: Int = context.resources.getIdentifier(
                    "ic_$value",
                    "drawable",
                    context.packageName
                )

                if (resourceId != 0) {
                    weatherIcon.setImageDrawable(
                        ContextCompat.getDrawable(context, resourceId)
                    )
                }
                binding.root.setOnClickListener {
                    onItemClicked(weatherEntity)
                }
            }
        }
    }
}
