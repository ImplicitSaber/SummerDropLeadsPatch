package io.github.implicitsaber.mod.summer_drop_leads_patch;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SummerDropLeadsPatch implements ModInitializer {

    public static final String MOD_ID = "summer_drop_leads_patch";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Summer Drop Leads Patch initialized!");
    }

}
