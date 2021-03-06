package com.googlecode.chartdroid.example.minimal.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;

import com.googlecode.chartdroid.core.ColumnSchema;
import com.googlecode.chartdroid.example.minimal.Market;
import com.googlecode.chartdroid.example.minimal.TemperatureData;

public class DataContentProvider extends ContentProvider {


	static final String TAG = Market.TAG;

	// Referencia del content provider - incluir en manifiesto.
	static final String AUTHORITY = "com.googlecode.chartdroid.example.minimal.provider";


	public static final Uri PROVIDER_URI = new Uri.Builder()
		.scheme(ContentResolver.SCHEME_CONTENT)
		.authority(AUTHORITY).build();


	@Override
	public boolean onCreate() {
		return true;
	}

	@Override
	public String getType(Uri uri) {
		return ColumnSchema.PlotData.CONTENT_TYPE_PLOT_DATA;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

		if (ColumnSchema.Aspect.DATASET_ASPECT_AXES.equals( uri.getQueryParameter(ColumnSchema.DATASET_ASPECT_PARAMETER) )) {

			MatrixCursor c = new MatrixCursor(new String[] {
					BaseColumns._ID,
            ColumnSchema.Aspect.Axes.COLUMN_AXIS_LABEL});

			int row_index = 0;
			for (int i=0; i<TemperatureData.DEMO_AXES_LABELS.length; i++) {

				c.newRow().add( row_index ).add( TemperatureData.DEMO_AXES_LABELS[i] );
				row_index++;
			}

			return c;
		} else if (ColumnSchema.Aspect.DATASET_ASPECT_SERIES.equals( uri.getQueryParameter(ColumnSchema.DATASET_ASPECT_PARAMETER) )) {

			// TODO: Define more columns for color, line style, marker shape, etc.
			MatrixCursor c = new MatrixCursor(new String[] {
					BaseColumns._ID,
					ColumnSchema.Aspect.Series.COLUMN_SERIES_LABEL});

			int row_index = 0;
			for (int i=0; i<TemperatureData.DEMO_TITLES.length; i++) {

				c.newRow().add( row_index ).add( TemperatureData.DEMO_TITLES[i] );
				row_index++;
			}

			return c;

		} else {
			// Configuración del cursor

			MatrixCursor c = new MatrixCursor(new String[] {
					BaseColumns._ID,
					ColumnSchema.Aspect.Data.COLUMN_AXIS_INDEX,
					ColumnSchema.Aspect.Data.COLUMN_SERIES_INDEX,
					ColumnSchema.Aspect.Data.COLUMN_DATUM_VALUE,
					ColumnSchema.Aspect.Data.COLUMN_DATUM_LABEL
			});

			int row_index = 0;

			// DATOS EJE X
			for (int i=0; i<TemperatureData.DEMO_X_AXIS_DATA.length; i++) {


				c.newRow()
				.add( row_index )
				.add( ColumnSchema.X_AXIS_INDEX )
				.add( 0 )   // Only create data for the first series.
				.add( TemperatureData.DEMO_X_AXIS_DATA[i] )
				.add( null );

				row_index++;
			}

			// DATOS EJE Y
			for (int i=0; i<TemperatureData.DEMO_SERIES_LIST.length; i++) {
				for (int j=0; j<TemperatureData.DEMO_SERIES_LIST[i].length; j++) {

					c.newRow()
					.add( row_index )
					.add( ColumnSchema.Y_AXIS_INDEX )
					.add( i )
					.add( TemperatureData.DEMO_SERIES_LIST[i][j] )
					.add( null );

					row_index++;
				}
			}

			return c;
		}
	}

	@Override
	public int update(Uri uri, ContentValues contentvalues, String s, String[] as) {
		throw new UnsupportedOperationException("Not supported by this provider");
	}


	@Override
	public Uri insert(Uri uri, ContentValues contentvalues) {
		throw new UnsupportedOperationException("Not supported by this provider");
	}

	@Override
	public int delete(Uri uri, String s, String[] as) {
		throw new UnsupportedOperationException("Not supported by this provider");
	}
}
