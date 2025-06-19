package tta.orderCollector.utils;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class OrderCollectorUtils {


	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
	//public static final SimpleDateFormat SMPDF = new SimpleDateFormat("yyyyMMdd-HH:mm:ss");
	public static final DecimalFormat CF = new DecimalFormat("000");
	public static final int Index_Level_Decimal = 7;
	
	public static String CurrencyFormat(double currency ){

		return OrderCollectorUtils.CF.format(currency*(java.lang.Math.pow(10,currency/OrderCollectorUtils.Index_Level_Decimal)));
	}
	
	public static String ordersPath;
	public static String urlReferentiel;
	public static String proxyHost;
	public static int proxyPort;
	
	public static RestTemplate restTemplate() {
		try {

			SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
			Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
			requestFactory.setProxy(proxy);
			return new RestTemplate(requestFactory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
