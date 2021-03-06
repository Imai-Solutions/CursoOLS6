/*
 * Copyright 2010-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.android.showcase.rest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.springframework.android.showcase.AbstractAsyncActivity;
import org.springframework.android.showcase.R;
import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * @author Roy Clarkson
 */
public class HttpGetGzipCompressedJsonActivity extends AbstractAsyncActivity {

	// ***************************************
	// Activity methods
	// ***************************************
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http_get_gzip_list_activity_layout);
	}

	@Override
	public void onStart() {
		super.onStart();
		new GzipRequestTask().execute();
	}

	// ***************************************
	// Private methods
	// ***************************************
	private void refreshResults(ResponseEntity<TwitterSearchResults> response) {
		if (response == null) {
			return;
		}

		HttpHeaders headers = response.getHeaders();
		StringBuilder sb = new StringBuilder();
		sb.append("Date: ").append(headers.getFirst("Date")).append("\n");
		sb.append("Status: ").append(headers.getFirst("Status")).append("\n");
		sb.append("Content-Type: ").append(headers.getFirst("Content-Type")).append("\n");
		sb.append("Content-Encoding: ").append(headers.getFirst("Content-Encoding")).append("\n");
		sb.append("Content-Length: ").append(headers.getFirst("Content-Length")).append("\n");

		TextView textView = (TextView) findViewById(R.id.text_view_headers);
		textView.setText(sb.toString());

		TwitterSearchResults results = response.getBody();

		ListView listView = (ListView) findViewById(R.id.list_view_result_items);
		listView.setAdapter(new TweetListAdapter(this, results.getResults()));
	}

	// ***************************************
	// Private classes
	// ***************************************
	private class GzipRequestTask extends AsyncTask<Void, Void, ResponseEntity<TwitterSearchResults>> {

		@Override
		protected void onPreExecute() {
			showLoadingProgressDialog();
		}

		@Override
		protected ResponseEntity<TwitterSearchResults> doInBackground(Void... params) {
			try {
				// The URL for making the GET request
				final String url = "https://api.twitter.com/1.1/search/tweets.json?q=%40noradio";

				// Add the gzip Accept-Encoding header to the request
				HttpHeaders requestHeaders = new HttpHeaders();
				requestHeaders.setAcceptEncoding(ContentCodingType.GZIP);

				// Create a new RestTemplate instance
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

				// Perform the HTTP GET request
				ResponseEntity<TwitterSearchResults> response = restTemplate.exchange(url, HttpMethod.GET,
						new HttpEntity<Object>(requestHeaders), TwitterSearchResults.class, "SpringSource");

				return response;
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
			}

			return null;
		}

		@Override
		protected void onPostExecute(ResponseEntity<TwitterSearchResults> result) {
			dismissProgressDialog();
			refreshResults(result);
		}

	}

}
