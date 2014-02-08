//package ca.techguys.takemynotes.beans;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.support.v4.view.ViewPager;
//import android.widget.ImageView;
//import android.widget.ListView;
//import ca.techguys.takemynotes.beans.AsyncImageLoader.ImageCallback;
//import ca.techguys.takemynotes.beans.AsyncImageLoader;
//
///**
// * @author kenny
// * 
// */
//public class MJKLoadingListsViewImage {
//	AsyncImageLoader asyncImageLoader;
//	private Context context;
//	private int mType;
//
//	public MJKLoadingListsViewImage(Context context) {
//		asyncImageLoader = new AsyncImageLoader(context);
//		this.context = context;
//	}
//
//	public MJKLoadingListsViewImage(Context context, int type) {
//		asyncImageLoader = new AsyncImageLoader(context);
//		this.context = context;
//		this.mType = type;
//	}
//
//	public void loadingImage(String imageUrl, final ImageView imageView, final int defaultImageRid,
//			final ListView listView) {
//		imageView.setTag(imageUrl);
//		Bitmap cachedImage = asyncImageLoader.loadDrawable(imageUrl, new ImageCallback() {
//			@Override
//			public void imageLoaded(Bitmap imageDrawable, String imageUrl) {
//				ImageView imageViewByTag = (ImageView) listView.findViewWithTag(imageUrl);
//				if (imageViewByTag != null) {
//					if ("".equals(imageUrl)) {
//						imageView.setImageResource(defaultImageRid);
//					} else {
//						try {
//							imageViewByTag.setImageBitmap(imageDrawable);
//						} catch (Exception e) {
//							imageView.setImageResource(defaultImageRid);
//							e.printStackTrace();
//						}
//					}
//				}
//
//			}
//		});
//
//		if (cachedImage != null) {
//			imageView.setImageBitmap(cachedImage);
//		} else {
//			imageView.setImageResource(defaultImageRid);
//		}
//	}
//
//	public void loadingImageOther(String imageUrl, final ImageView imageView,
//			final int defaultImageRid, final ViewPager viewPager) {
//		imageView.setTag(imageUrl);
//		Bitmap cachedImage = asyncImageLoader.loadDrawable(imageUrl, new ImageCallback() {
//			@Override
//			public void imageLoaded(Bitmap imageDrawable, String imageUrl) {
//				ImageView imageViewByTag = (ImageView) viewPager.findViewWithTag(imageUrl);
//				if (imageViewByTag != null) {
//					if ("".equals(imageUrl)) {
//						imageView.setImageResource(defaultImageRid);
//					} else {
//						try {
//							imageViewByTag.setImageBitmap(imageDrawable);
//						} catch (Exception e) {
//							imageView.setImageResource(defaultImageRid);
//							e.printStackTrace();
//						}
//					}
//				}
//
//			}
//		});
//
//		if (cachedImage == null) {
//			imageView.setImageResource(defaultImageRid);
//		} else {
//			imageView.setImageBitmap(cachedImage);
//		}
//	}
//
//	public void loadingImageView(String imageUrl, final ImageView imageView,
//			final int defaultImageRid) {
//		Bitmap cachedImage = asyncImageLoader.loadDrawable(imageUrl, new ImageCallback() {
//			@Override
//			public void imageLoaded(Bitmap imageDrawable, String imageUrl) {
//				if ("".equals(imageUrl)) {
//					imageView.setImageResource(defaultImageRid);
//				} else {
//					try {
//						imageView.setImageBitmap(imageDrawable);
//					} catch (Exception e) {
//						imageView.setImageResource(defaultImageRid);
//						e.printStackTrace();
//					}
//				}
//
//			}
//		});
//
//		if (cachedImage == null) {
//			imageView.setImageResource(defaultImageRid);
//		} else {
//			imageView.setImageBitmap(cachedImage);
//		}
//	}
//
//}