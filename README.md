# FlickrMVP
A project demonstrating the usage of MVP Architecture in Android framework to efficently pull images via Flickr API. The project is using the following libraries:

- Dagger1 
- Otto EventBus 
- RetroFit

Bitmap decoding is handled on a custom background thread that communicates with the main thread. This was decided for educational purposes only. A more manageable library such as Picasso could easily be implemented instead, into the custom PhotoAdapter.

A basic implementation of image caching is used to eliminate reoccurrance of photo downloading.

History (Clicked) photos are serialized into a json file residing on the device. This feature is adaptable to different back-end storage, which can communicate through the PhotoInteractor class.

ViewPager main screen:
![Animations and Refresh](https://github.com/futureproofd/FlickrMVP/blob/master/assets/screencaps/home.png)

Different navigational animations (Including Refresh):
![Animations and Refresh](https://github.com/futureproofd/FlickrMVP/blob/master/assets/screencaps/features.png)

Supports orientation change:
![Animations and Refresh](https://github.com/futureproofd/FlickrMVP/blob/master/assets/screencaps/features2.png)
