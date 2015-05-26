// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.maven.job.setting.project.initializer;

import java.io.IOException;

import org.eclipse.jface.preference.IPreferenceStore;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.designer.maven.job.MavenJobPlugin;
import org.talend.designer.maven.template.IProjectSettingPreferenceConstants;
import org.talend.designer.maven.template.MavenTemplateConstants;
import org.talend.designer.maven.template.MavenTemplateManager;
import org.talend.designer.maven.ui.setting.project.initializer.AbstractProjectPreferenceInitializer;

/**
 * DOC ggu class global comment. Detailled comment
 * 
 */
public class MavenJobScriptsProjectSettingInitializer extends AbstractProjectPreferenceInitializer {

    @Override
    protected IPreferenceStore getPreferenceStore() {
        return MavenJobPlugin.getDefault().getProjectPreferenceManager().getPreferenceStore();
    }

    @Override
    protected void initializeFields(IPreferenceStore preferenceStore) {
        super.initializeFields(preferenceStore);

        try {
            String pomJobContent = MavenTemplateManager
                    .getBundleTemplateContent(MavenTemplateConstants.POM_JOB_TEMPLATE_FILE_NAME);
            preferenceStore.setDefault(IProjectSettingPreferenceConstants.TEMPLATE_STANDALONE_JOB_POM, pomJobContent);

            String assemblyContent = MavenTemplateManager
                    .getBundleTemplateContent(MavenTemplateConstants.ASSEMBLY_JOB_TEMPLATE_FILE_NAME);
            preferenceStore.setDefault(IProjectSettingPreferenceConstants.TEMPLATE_STANDALONE_JOB_ASSEMBLY,
                    assemblyContent);
        } catch (IOException e) {
            ExceptionHandler.process(e);
        }

    }

}
