package ohrm.malgra.model;

import com.google.common.collect.HashBiMap;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Toby on 18/04/2016.
 */
public class ModelBakeHandler {

    public static final ModelBakeHandler instance = new ModelBakeHandler();

    @SubscribeEvent
    public void onModelBakeEvent(ModelBakeEvent event) {
        Object object = event.getModelRegistry().getObject(ExtractorModel.baseModelResourceLocation);
        Map<Item, IBakedModel> containerModels = new ConcurrentHashMap<Item, IBakedModel>();
        Iterator containerModelResourceLocations = ExtractorModel.containerModelResourceLocations.entrySet().iterator();
        while (containerModelResourceLocations.hasNext()) {
            Map.Entry<Item, ModelResourceLocation> pair = (Map.Entry<Item, ModelResourceLocation>)containerModelResourceLocations.next();
            ModelResourceLocation loc = pair.getValue();
            IBakedModel model = event.getModelRegistry().getObject(pair.getValue());
            containerModels.put(pair.getKey(), event.getModelRegistry().getObject(pair.getValue()));
            containerModelResourceLocations.remove();
        }
        Map<Item, IBakedModel> tipModels = new ConcurrentHashMap<Item, IBakedModel>();
        Iterator tipModelResourceLocations = ExtractorModel.tipModelResourceLocations.entrySet().iterator();
        while (tipModelResourceLocations.hasNext()) {
            Map.Entry<Item, ModelResourceLocation> pair = (Map.Entry<Item, ModelResourceLocation>)tipModelResourceLocations.next();
            tipModels.put(pair.getKey(), event.getModelRegistry().getObject(pair.getValue()));
            tipModelResourceLocations.remove();
        }

        if (object instanceof IBakedModel) {
            IBakedModel existingModel = (IBakedModel)object;
            ExtractorModel customModel = new ExtractorModel(existingModel, containerModels, tipModels);
            event.getModelRegistry().putObject(ExtractorModel.baseModelResourceLocation, customModel);
            Object object2 = event.getModelRegistry().getObject(ExtractorModel.baseModelResourceLocation);
            object2.hashCode();
        }
    }

}
