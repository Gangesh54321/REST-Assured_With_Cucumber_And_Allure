package Utils;

public class Context {
	private static String accessToken;
	private static String placeID;
	private static String name;

	public static String getAccessToken() {
		return accessToken;
	}

	public static void setAccessToken(String accessToken) {
		Context.accessToken = accessToken;
	}

	public static String getPlaceID() {
		return placeID;
	}

	public static void setPlaceID(String placeID) {
		Context.placeID = placeID;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Context.name = name;
	}



}
