package com.shenzhen.housing.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shenzhen.housing.R
import com.shenzhen.housing.ui.components.GuideCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("全部") }
    
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.application_title)) },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // 搜索栏
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("搜索申请指南") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    singleLine = true
                )
            }
            
            item {
                // 类型筛选
                Text(
                    text = "指南类型",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(getGuideTypes()) { type ->
                        FilterChip(
                            onClick = { selectedType = type },
                            label = { Text(type) },
                            selected = selectedType == type
                        )
                    }
                }
            }
            
            item {
                Text(
                    text = "申请指南",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            items(getGuideItems()) { guide ->
                GuideCard(
                    title = guide.title,
                    content = guide.content,
                    type = guide.type,
                    steps = guide.steps
                )
            }
        }
    }
}

data class GuideItem(
    val title: String,
    val content: String,
    val type: String,
    val steps: List<String>
)

fun getGuideTypes(): List<String> = listOf(
    "全部", "申请条件", "申请流程", "所需材料", "时间安排"
)

fun getGuideItems(): List<GuideItem> = listOf(
    GuideItem(
        "保障性住房申请条件",
        "了解申请保障性住房需要满足的基本条件",
        "申请条件",
        listOf(
            "具有深圳市户籍或持有深圳市居住证",
            "家庭人均年收入不超过规定标准",
            "家庭人均住房建筑面积不超过规定标准",
            "未享受过其他住房保障政策"
        )
    ),
    GuideItem(
        "保障性住房申请流程",
        "详细的申请步骤和操作指南",
        "申请流程",
        listOf(
            "在线注册并填写申请表",
            "上传相关证明材料",
            "提交申请并等待审核",
            "审核通过后参与摇号选房",
            "签订租赁合同并办理入住"
        )
    ),
    GuideItem(
        "申请所需材料清单",
        "准备申请时需要提交的所有材料",
        "所需材料",
        listOf(
            "身份证原件及复印件",
            "户口本原件及复印件",
            "收入证明",
            "住房情况证明",
            "婚姻状况证明",
            "其他相关证明材料"
        )
    ),
    GuideItem(
        "申请时间安排",
        "了解各阶段的时间节点和注意事项",
        "时间安排",
        listOf(
            "申请期：每年3-4月",
            "审核期：申请截止后2个月内",
            "摇号期：审核通过后1个月内",
            "选房期：摇号结果公布后1个月内",
            "入住期：选房后1个月内"
        )
    )
)
