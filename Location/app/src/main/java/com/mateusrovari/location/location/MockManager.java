package com.mateusrovari.location.location;

import android.location.Location;
import android.os.SystemClock;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MockManager {

	private GoogleApiClient apiClient;
	private boolean mockMode;
	private transient boolean running;

	public MockManager(GoogleApiClient apiClient) {
		this.apiClient = apiClient;
	}
	
	public void setLocation(double latitude, double longitude) throws SecurityException {
		if (!mockMode) {
            LocationServices.FusedLocationApi.setMockMode(apiClient, true);
			mockMode = true;
		}
		
		Location mockLocation = new Location("MockProvider");
		mockLocation.setLatitude(latitude);
		mockLocation.setLongitude(longitude);
		mockLocation.setAccuracy(1.0f);
		mockLocation.setTime(System.currentTimeMillis());
		mockLocation.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        LocationServices.FusedLocationApi.setMockLocation(apiClient, mockLocation);
	}
	
	public void startThread(final double latitude, final double longitude, final double amount) {
        running = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				double currentLatitude = latitude;
				double currentLongitude = longitude;
				
				while (running) {
					setLocation(currentLatitude, currentLongitude);
					currentLatitude += amount;
					currentLongitude += amount;
					SystemClock.sleep(500);
				}
			}
		}).start();
	}

	public void dispose() {
        if (running) {
            running = false;
            SystemClock.sleep(1000);
        }
	}
}
