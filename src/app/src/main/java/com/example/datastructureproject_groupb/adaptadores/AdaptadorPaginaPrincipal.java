package com.example.datastructureproject_groupb.adaptadores;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datastructureproject_groupb.Bocu;
import com.example.datastructureproject_groupb.ImplementacionesEstructurasDeDatos.DynamicUnsortedList;
import com.example.datastructureproject_groupb.PaginaPrincipal;
import com.example.datastructureproject_groupb.R;
import com.example.datastructureproject_groupb.entidades.Evento;

public class AdaptadorPaginaPrincipal extends RecyclerView.Adapter<AdaptadorPaginaPrincipal.EventoViewHolder> {

    DynamicUnsortedList<Evento> listaEventos;

    public AdaptadorPaginaPrincipal(DynamicUnsortedList<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }
    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_eventos_pagina_principal, null, false);

        return new EventoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
            Evento evento = listaEventos.get(position);

            holder.textViewTituloEvento.setText(evento.getNombreEvento());
            holder.textViewFechaEvento.setText("Fecha: " + evento.getFechaEventoString());
            holder.textViewHorarioEvento.setText("Horario: " + evento.getHorarioEvento());
            holder.textViewLugarEvento.setText("Lugar: " + evento.getUbicacionEvento());
            holder.textViewCostoEvento.setText("Costo: " + evento.getCostoEventoConFormato());
            holder.textViewTipoEvento.setText("Tipo: " + Bocu.INTERESES[evento.getCategoriaEvento()]);
    }

    @Override
    public int getItemCount() {
        return listaEventos.size();

    }

    public class EventoViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTituloEvento, textViewFechaEvento,textViewHorarioEvento, textViewLugarEvento, textViewCostoEvento, textViewTipoEvento;

        public EventoViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTituloEvento = itemView.findViewById(R.id.textViewTituloEventoPaginaPrincipal);
            textViewFechaEvento = itemView.findViewById(R.id.textViewFechaEventoPaginaPrincipal);
            textViewHorarioEvento = itemView.findViewById(R.id.textViewHorarioEventoPaginaPrincipal);
            textViewLugarEvento = itemView.findViewById(R.id.textViewLugarEventoPaginaPrincipal);
            textViewCostoEvento = itemView.findViewById(R.id.textViewCostoEventoPaginaPrincipal);
            textViewTipoEvento = itemView.findViewById(R.id.textViewTipoEventoPaginaPrincipal);

            itemView.setOnClickListener(v -> {

                Evento evento = listaEventos.get(getAdapterPosition());

                Activity activity = (Activity) v.getContext();
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                LayoutInflater inflater = activity.getLayoutInflater();
                View view = inflater.inflate(R.layout.view_evento_presionado_pagina_principal, null);
                TextView tituloEvento = view.findViewById(R.id.textViewTituloEventoPaginaPrincipal),
                        fechaEvento = view.findViewById(R.id.textViewFechaEventoPaginaPrincipal),
                        horarioEvento = view.findViewById(R.id.textViewHorarioEventoPaginaPrincipal),
                        lugarEvento = view.findViewById(R.id.textViewLugarEventoPaginaPrincipal),
                        costoEvento = view.findViewById(R.id.textViewCostoEventoPaginaPrincipal),
                        tipoEvento = view.findViewById(R.id.textViewTipoEventoPaginaPrincipal),
                        descripcionEvento = view.findViewById(R.id.textViewDescripcionEventoPaginaPrincipal);

                tituloEvento.setText(evento.getNombreEvento());
                fechaEvento.setText("Fecha: " + evento.getFechaEventoString());
                horarioEvento.setText("Horario: " + evento.getHorarioEvento());
                lugarEvento.setText("Lugar: " + evento.getUbicacionEvento());
                costoEvento.setText("Costo: " + evento.getCostoEventoConFormato());
                tipoEvento.setText("Tipo: " + evento.getCategoriaEvento());
                descripcionEvento.setText("Descripción: " + evento.getDescripcionEvento());

                builder.setView(view);

                builder.show();

                PaginaPrincipal.historialEventos.push(evento);

            });

        }
    }
}