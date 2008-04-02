// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.core.ui.editor.properties.controllers;

import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.talend.core.model.metadata.builder.connection.Connection;
import org.talend.core.model.process.IElementParameter;
import org.talend.core.model.properties.ConnectionItem;
import org.talend.core.model.repository.ERepositoryObjectType;
import org.talend.designer.core.model.components.EParameterName;
import org.talend.designer.core.model.components.EmfComponent;
import org.talend.designer.core.ui.editor.cmd.ChangeValuesFromRepository;
import org.talend.designer.core.ui.editor.properties.controllers.generator.IDynamicProperty;
import org.talend.repository.ui.dialog.RepositoryReviewDialog;

/**
 * DOC nrousseau class global comment. Detailled comment
 */
public class PropertyTypeController extends AbstractRepositoryController {

    public PropertyTypeController(IDynamicProperty dp) {
        super(dp);
    }

    /*
     * (non-Javadoc)
     * 
     * 
     * @see org.talend.designer.core.ui.editor.properties.controllers.AbstractRepositoryController#createButtonCommand(org.eclipse.swt.widgets.Button)
     */
    @Override
    protected Command createButtonCommand(Button button) {
        if (button.getData(NAME).equals(REPOSITORY_CHOICE)) {
            String paramName = (String) button.getData(PARAMETER_NAME);
            IElementParameter param = elem.getElementParameter(paramName);
            RepositoryReviewDialog dialog = new RepositoryReviewDialog(Display.getCurrent().getActiveShell(),
                    ERepositoryObjectType.METADATA, param.getRepositoryValue());
            if (dialog.open() == RepositoryReviewDialog.OK) {
                String id = dialog.getResult().getObject().getId();

                IElementParameter repositoryParam = param.getChildParameters().get(
                        EParameterName.REPOSITORY_PROPERTY_TYPE.getName());
                if (repositoryParam != null) {
                    repositoryParam.setLinkedRepositoryItem(dialog.getResult().getObject().getProperty().getItem());
                }
                String fullParamName = paramName + ":" + getRepositoryChoiceParamName(); //$NON-NLS-1$

                Connection repositoryConnection = null;
                Map<String, ConnectionItem> repositoryConnectionItemMap = dynamicProperty.getRepositoryConnectionItemMap();

                if (repositoryConnectionItemMap.containsKey(id)) {
                    repositoryConnection = repositoryConnectionItemMap.get(id).getConnection();
                } else {
                    repositoryConnection = null;
                }

                if (repositoryConnection != null) {
                    ChangeValuesFromRepository changeValuesFromRepository = new ChangeValuesFromRepository(elem,
                            repositoryConnection, fullParamName, id);
                    changeValuesFromRepository.setMaps(dynamicProperty.getRepositoryTableMap());
                    return changeValuesFromRepository;
                }

            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ui.editor.properties.controllers.AbstractRepositoryController#createComboCommand(org.eclipse.swt.custom.CCombo)
     */
    @Override
    protected Command createComboCommand(CCombo combo) {
        Connection repositoryConnection = null;

        String paramName = (String) combo.getData(PARAMETER_NAME);

        IElementParameter param = elem.getElementParameter(paramName);

        String value = combo.getText();

        for (int j = 0; j < param.getListItemsValue().length; j++) {
            if (combo.getText().equals(param.getListItemsDisplayName()[j])) {
                value = (String) param.getListItemsValue()[j];
            }
        }
        if (value.equals(param.getValue())) {
            return null;
        }
        Map<String, ConnectionItem> repositoryConnectionItemMap = null;

        if (value.equals(EmfComponent.REPOSITORY)) {
            repositoryConnectionItemMap = dynamicProperty.getRepositoryConnectionItemMap();

            IElementParameter repositoryParam = param.getParentParameter().getChildParameters().get(
                    EParameterName.REPOSITORY_PROPERTY_TYPE.getName());
            String connectionSelected = (String) repositoryParam.getValue();

            if (repositoryConnectionItemMap.containsKey(connectionSelected)) {
                repositoryConnection = (org.talend.core.model.metadata.builder.connection.Connection) repositoryConnectionItemMap
                        .get(connectionSelected).getConnection();
            } else {
                repositoryConnection = null;
            }
        }
        ChangeValuesFromRepository changeValuesFromRepository = new ChangeValuesFromRepository(elem, repositoryConnection,
                paramName, value);

        changeValuesFromRepository.setMaps(dynamicProperty.getRepositoryTableMap());
        return changeValuesFromRepository;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ui.editor.properties.controllers.AbstractRepositoryController#getRepositoryChoiceParamName()
     */
    @Override
    protected String getRepositoryChoiceParamName() {
        return EParameterName.REPOSITORY_PROPERTY_TYPE.getName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.designer.core.ui.editor.properties.controllers.AbstractRepositoryController#getRepositoryTypeParamName()
     */
    @Override
    protected String getRepositoryTypeParamName() {
        return EParameterName.PROPERTY_TYPE.getName();
    }

    // @Override
    // protected String getDisplayNameFromValue(IElementParameter param, String value) {
    // if (param == null || value == null || value.equals("")) { //$NON-NLS-1$
    // return null;
    // }
    // if (!param.getName().equals(getRepositoryChoiceParamName())) {
    // return null;
    // }
    // Item item = param.getLinkedRepositoryItem();
    // if (item == null || (item != null && !item.getProperty().getId().equals(value))) {
    // Map<String, ConnectionItem> itemMap = dynamicProperty.getRepositoryConnectionItemMap();
    // item = itemMap.get(value);
    // if (item == null) {
    // item = UpdateRepositoryUtils.getConnectionItemByItemId(value);
    // if (item != null) {
    // // set in map
    // itemMap.put(value, (ConnectionItem) item);
    // }
    // }
    // }
    // if (item != null && item instanceof ConnectionItem) {
    // return dynamicProperty.getRepositoryAliasName((ConnectionItem) item) + ":" + item.getProperty().getLabel();
    // //$NON-NLS-1$
    // }
    // return null;
    // }

}
