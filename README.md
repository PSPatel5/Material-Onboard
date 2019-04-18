# Material Onboard

This is demo library for personal use to make introduction of your application more beautiful. Developer can add 6 different types of pages in material introduction which are listed in the features section.


# Features!

  - Default template with basic usage. ( Title , Image , Description )
  - Default template with advance parameters. ( Color, Font , Background , Text Size etc )
  - Permission fragment with basic usage. ( Image , Description , Permission Name )
  - Permission fragment with advance parameters. ( Color, Size , Background )
  - User defined custom fragments.
  - User defined custom layouts.

You can also:
  - Both horizontal and vertical scroll available
  - Show / Hide indicators
  - Show / Hide Previous Button
  - Show / Hide Next Button
  - Show / Hide Skip Button

A good design is always crucial in software developement process. Google introduced material design with api 21 back in 2015. About designing As Irene au quotes,

> Good design is like a refrigerator when it works , 
no one notices but when it doesn’t , it sure stinks.

### Installation

Material intro requies [Design Support Library](https://developer.android.com/topic/libraries/support-library/packages#design)  to run.

Put below line in your project level Gradle.

```sh
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Put below line in your app level Gradle.

```sh
implementation 'com.github.techtainer-psp:Material-Onboard:v1.0'
```

### Other Libraries

Thanks to  [Roman Danylyk](https://github.com/romandanylyk) as i have used his [PageIndicatorView](https://github.com/romandanylyk/PageIndicatorView)  for page indication in this library.
Material Onboard uses these other libraries and they are required to make this library function properly.
* [Design Support Library] - For snakbar when permission is denied by user !
* [Constraint Layout] - For Layout designing.

```sh
implementation 'com.android.support:design:28.0.0'
implementation 'com.android.support.constraint:constraint-layout:1.1.2'
```

### Issues
While using this library if you face any issues report use at techtainer.psp@gmail.com

### Licence 

It is free library for everyone use it as you required.
