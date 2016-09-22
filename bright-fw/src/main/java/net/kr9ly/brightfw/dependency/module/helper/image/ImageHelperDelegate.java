package net.kr9ly.brightfw.dependency.module.helper.image;

import net.kr9ly.brightfw.helper.hook.HookHelper;
import net.kr9ly.brightfw.helper.image.ImageHelper;

public interface ImageHelperDelegate {

    ImageHelper imageHelper(HookHelper hookHelper);
}
