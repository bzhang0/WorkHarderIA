package com.example.workharderia;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public ExerciseAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        public TextView exerciseText;
        public TextView repCountText;
        public TextView weightCountText;


        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);

            exerciseText = itemView.findViewById(R.id.textview_name_exercise);
            repCountText = itemView.findViewById(R.id.textview_reps_exercise);
            weightCountText = itemView.findViewById(R.id.textview_weight_exercise);
        }
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_display_workout, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        String name = mCursor.getString(mCursor.getColumnIndex(WorkoutContract.WorkoutEntry.COLUMN_EXERCISENAME));
        int reps = mCursor.getInt(mCursor.getColumnIndex(WorkoutContract.WorkoutEntry.COLUMN_REPS));
        int weight = mCursor.getInt(mCursor.getColumnIndex(WorkoutContract.WorkoutEntry.COLUMN_WEIGHT));

        holder.exerciseText.setText(name);
        holder.repCountText.setText(String.valueOf(reps));
        holder.weightCountText.setText(String.valueOf(weight));
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}
