package manager;

import java.util.HashMap;
import java.util.Map;

public class ImageManager {

    private static Map<String, ImageData> images;

    public static void init(){
        images = new HashMap<>();
        
        for(ImageData data : ImageDatas.DATAS){
            images.put(data.name, data);
            data.loadImages();
        }
    }

    public static ImageData get(String name){
        return images.get(name);
    }

}
