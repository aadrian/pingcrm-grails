package pingcrm.image

import com.drew.imaging.ImageMetadataReader
import com.drew.metadata.exif.ExifIFD0Directory
import groovy.transform.CompileStatic

import java.awt.image.BufferedImage

@CompileStatic
trait ImageProcessor {

    abstract BufferedImage resize(BufferedImage src, int targetWidth, int targetHeight, ResizingMode resizeMode, ScalingQuality scalingQuality)
    abstract BufferedImage rotate(BufferedImage src, Rotation rotation)

    Rotation getImageRotation(File file) {

        def rotation = Rotation.NONE

        def metadata = ImageMetadataReader.readMetadata(file)
        if(metadata.containsDirectoryOfType(ExifIFD0Directory)) {

            def exifIFD0 = metadata.getDirectoriesOfType(ExifIFD0Directory)
            def orientation = exifIFD0.find { it.containsTag(ExifIFD0Directory.TAG_ORIENTATION) }?.getInt(ExifIFD0Directory.TAG_ORIENTATION)

            if(orientation) {
                switch (orientation) {
                    case 6: rotation = Rotation.CW_90; break // [Exif IFD0] Orientation - Right side, top (Rotate 90 CW)
                    case 3: rotation = Rotation.CW_180; break // [Exif IFD0] Orientation - Bottom, right side (Rotate 180)
                    case 8: rotation = Rotation.CW_270; break // [Exif IFD0] Orientation - Left side, bottom (Rotate 270 CW)
                    default: break
                }
            }
        }

        rotation
    }

    enum Rotation {

        NONE('0'),
        CW_90('90'),
        CW_180('180'),
        CW_270('270')

        private final String value

        private Rotation(String value) { this.value = value }
        static Rotation get(Object value) { values().find { it.value == value } ?: NONE }
        static List<String> getValidValues() { values()*.value }
        String getValue() { value }
        String toString() { value }
    }

    enum ResizingMode {

        AUTOMATIC('auto'),
        FIT_EXACT('exact'),
        FIT_TO_WIDTH('fity'),
        FIT_TO_HEIGHT('fitx'),
        CROP('crop')

        private final String value

        private ResizingMode(String value) { this.value = value }
        static ResizingMode get(Object value) { values().find { it.value == value } ?: AUTOMATIC }
        static List<String> getValidValues() { values()*.value }
        String getValue() { value }
        String toString() { value }
    }

    enum ScalingQuality {

        AUTOMATIC('auto'),
        SPEED('speed'),
        BALANCED('balanced'),
        QUALITY('quality'),
        ULTRA_QUALITY('ultra-quality')

        private final String value

        private ScalingQuality(String value) { this.value = value }
        static ScalingQuality get(Object value) { values().find {it.value == value } ?: AUTOMATIC }
        static List<String> getValidValues() { values()*.value }
        String getValue() { value }
        String toString() { value }
    }
}