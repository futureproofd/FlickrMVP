# FlickrMVP
An MVP test project to get images from Flickr API, using Dagger, Otto EventBus, and RetroFit.

Bitmap decoding is handled on a custom background thread that communicates with the main thread. This was decided for educational purposes only. A more manageable library such as Picasso could easily be implemented instead, into the custom PhotoAdapter.

Work in progress.
