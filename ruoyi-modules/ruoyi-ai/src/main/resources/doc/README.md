# 项目详细说明文档 & 底层基础对话规则

## 第一部分：底层基础对话规则

为了确保后续开发与维护的规范性，本部分记录了 AI 助手（Antigravity）与用户之间底层基础对话规则。**本规则一旦确定，后续对话中将严格遵守，非经用户明确指令，不得随意违背或修改。**

1. **禁止改动代码**：
   - AI 助手在当前阶段及后续交互中，**绝对不可修改任何项目代码文件**（包括 Java 代码、XML 配置、Maven POM 文件等），所有操作仅限于生成和维护文档。
2. **文档存放目录**：
   - 所有的说明文档、设计文档、交互记录规则等，必须统一存放在指定目录：
     [`ruoyi-modules/ruoyi-ai/src/main/resources/doc/`](file:///Users/ulpon/Documents/project/ulpon/ulpon-backend/ruoyi-modules/ruoyi-ai/src/main/resources/doc/)
3. **单文档维护原则（追加/合并写入）**：
   - 除非用户明确指示生成新的独立文件，否则所有新生成的说明文档、规则更新、以及后续的补充说明，**必须合并写入到本单一文档中**（即：[`README.md`](file:///Users/ulpon/Documents/project/ulpon/ulpon-backend/ruoyi-modules/ruoyi-ai/src/main/resources/doc/README.md)），采用追加或更新小节的方式，避免零散文件污染目录。
4. **严格的规则遵守**：
   - AI 助手在执行后续任务时，每次开始对话和操作前必须优先读取并遵循此处的对话规则，绝不能以任何理由违背。

---

## 第二部分：`ruoyi-ai` 模块详细说明文档

### 1. 模块概述
`ruoyi-ai` 是基于 RuoYi-Vue-Plus 框架微服务/单体版开发的 **AI 智能体及大语言模型（LLM）集成业务模块**。
该模块主要集成了以下两大核心技术框架：
- **Snail AI 集成**：对接 Snail AI Platform，具备 Snail AI OpenAPI 用户注册、智能体客户端模式交互、嵌入式 Chat UI 调试以及 OpenAPI 接口调用等能力。
- **LangChain4j 集成**：引入 `langchain4j` 开发组件，通过兼容模式快速适配国内外主流大模型（如阿里通义千问 Qwen 等），提供统一的大模型服务能力。

---

### 2. 模块目录结构与主要文件

#### 2.1 业务模块 `ruoyi-modules/ruoyi-ai`
- [`pom.xml`](file:///Users/ulpon/Documents/project/ulpon/ulpon-backend/ruoyi-modules/ruoyi-ai/pom.xml)：声明了对 `ruoyi-common-core`、`ruoyi-api`、`ruoyi-common-ai`、`ruoyi-common-satoken`、`ruoyi-common-web` 的依赖，以及 `langchain4j` 相关的 Starter 包。
- `src/main/java/org/dromara/ai/`：
  - `config/`
    - [`BaseAiConfig.java`](file:///Users/ulpon/Documents/project/ulpon/ulpon-backend/ruoyi-modules/ruoyi-ai/src/main/java/org/dromara/ai/config/BaseAiConfig.java)：基础 AI 配置占位类，预留做后续扩展。
    - [`LangChainConfig.java`](file:///Users/ulpon/Documents/project/ulpon/ulpon-backend/ruoyi-modules/ruoyi-ai/src/main/java/org/dromara/ai/config/LangChainConfig.java)：LangChain4j 适配配置，带有 main 测试方法，用于测试集成兼容阿里通义千问（DashScope 兼容模式）的 `qwen3.7-flash` 模型。
  - `controller/`
    - [`SnailAiController.java`](file:///Users/ulpon/Documents/project/ulpon/ulpon-backend/ruoyi-modules/ruoyi-ai/src/main/java/org/dromara/ai/controller/SnailAiController.java)：Snail AI OpenAPI 外部注册和交互控制器，暴露出当前登录用户向 Snail AI 平台自动注册的 POST 接口 `/snail-ai/user/register`。
  - `service/`
    - [`IAiAgentService.java`](file:///Users/ulpon/Documents/project/ulpon/ulpon-backend/ruoyi-modules/ruoyi-ai/src/main/java/org/dromara/ai/service/IAiAgentService.java)：智能体操作的业务接口声明（包含 info, list, create, update, delete 接口方法）。
    - `impl/`
      - [`AiAgentServiceImpl.java`](file:///Users/ulpon/Documents/project/ulpon/ulpon-backend/ruoyi-modules/ruoyi-ai/src/main/java/org/dromara/ai/service/impl/AiAgentServiceImpl.java)：智能体操作的业务接口实现类（实现了 `org.dromara.common.ai.service.IAiAgentService`，目前为空实现）。
      - [`BaseAiChatService.java`](file:///Users/ulpon/Documents/project/ulpon/ulpon-backend/ruoyi-modules/ruoyi-ai/src/main/java/org/dromara/ai/service/impl/BaseAiChatService.java)：基础 AI 对话服务占位类。
- `src/main/resources/`
  - `doc/`
    - [`README.md`](file:///Users/ulpon/Documents/project/ulpon/ulpon-backend/ruoyi-modules/ruoyi-ai/src/main/resources/doc/README.md)：（本文件）项目详细说明文档与底层对话规则。

#### 2.2 公共依赖模块 `ruoyi-common/ruoyi-common-ai`
- [`pom.xml`](file:///Users/ulpon/Documents/project/ulpon/ulpon-backend/ruoyi-common/ruoyi-common-ai/pom.xml)：声明了对 `ruoyi-common-core` 的依赖，并引入了 Snail AI 的三方 SDK 包：
  - `com.aizuda:snail-ai-agent-chat-starter`
  - `com.aizuda:snail-ai-agent-executor-starter`
  - `com.aizuda:snail-ai-openapi-starter`
- `src/main/java/org/dromara/common/ai/`：
  - `config/`
    - [`SnailAiConfig.java`](file:///Users/ulpon/Documents/project/ulpon/ulpon-backend/ruoyi-common/ruoyi-common-ai/src/main/java/org/dromara/common/ai/config/SnailAiConfig.java)：Snail AI 自动配置类，利用 `@EnableSnailAiAgent` 和 `@EnableSnailAiOpenApi` 启用 Snail AI SDK 功能，在系统属性 `snail-ai.enabled` 为 `true` 时生效。

---

### 3. Snail AI 的整合与配置机制

#### 3.1 详细配置参数说明
系统在 [`application-dev.yml`](file:///Users/ulpon/Documents/project/ulpon/ulpon-backend/ruoyi-admin/src/main/resources/application-dev.yml#L31-L77) 与 `application-prod.yml` 中提供了对 Snail AI 服务的深度配置项：
```yaml
snail-ai:
  enabled: false                      # 是否启用客户端模式（默认关闭）
  # ==================== Server 连接配置 ====================
  server:
    host: 127.0.0.1                  # Snail AI Server 端 gRPC 服务端 IP 地址
    port: 18888                      # Snail AI Server 端 gRPC 服务的端口
  # ==================== 客户端配置 ====================
  app-id: 1                          # 在 Snail AI Server 平台注册的应用 ID
  token: SAI_566a6bfbc26e4998b4841c # Snail AI Server 自动生成的应用认证令牌
  port: 3${server.port}              # 当前微服务/业务系统的 gRPC 端口，用于 Server 往本客户端分发请求
  skill-temp-dir: /tmp/snail-ai-agent/skills # 智能体技能文件的临时存放位置
  # ==================== Chat 嵌入模式配置 ====================
  chat.ui.embed:
    enabled: true                    # 启用 Chat UI 的嵌入模式
    show-header: false               # 嵌入时隐藏 UI 的顶部栏
    show-sidebar-user: false         # 嵌入时隐藏侧边栏的用户信息
    show-agent-market: true          # 嵌入时依然展示智能体市场入口
    compact-input: true              # 启用紧凑式输入框
    lock-agent: false                # 是否锁定当前智能体，不让用户自由切换
  # ==================== OpenAPI 客户端配置 ====================
  open-api:
    enabled: true                    # 启用 OpenAPI 客户端
    web-port: 8900                   # Snail AI Server 端的 HTTP Web 端口
    https: false                     # 是否使用 HTTPS 协议
    prefix: snail-ai                 # 暴露出的 API 路径前缀（controller 基础路由）
    connect-timeout-ms: 5000         # 连接超时时长（毫秒）
    read-timeout-ms: 60000           # 数据读取超时（毫秒）
    chat-timeout-ms: 300000          # 对话超时（毫秒）
```

#### 3.2 用户注册流程与鉴权
- 控制器 [`SnailAiController`](file:///Users/ulpon/Documents/project/ulpon/ulpon-backend/ruoyi-modules/ruoyi-ai/src/main/java/org/dromara/ai/controller/SnailAiController.java) 整合了 RuoYi 系统自身的 Sa-Token 安全框架。
- 当用户调用 `/snail-ai/user/register` 接口时：
  1. 通过 `LoginHelper.getUserId()` 与 `LoginHelper.getLoginUser()` 获取当前 RuoYi 系统内已登录用户的 ID 和 Nickname。若未登录则抛出 `SnailAiException`。
  2. 构建 `OpenApiUserRegisterRequest`，将 RuoYi 用户的 `userId` 填入 Snail AI 的 `externalId`，将 `nickname` 作为昵称。
  3. 调用 Snail AI 的 `OpenApiUserClient.register(...)` 客户端服务。
  4. 验证 Snail AI 返回的接口状态 `status`。若为 `SNAIL_AI_SUCCESS` (1)，则将注册成功的 OpenAPI 用户信息 `OpenApiUserVO` 返回给前端；否则抛出异常。

---

### 4. LangChain4j 的整合与测试

- 模块中引入了官方推荐的 Spring Boot 集成组件：
  - `langchain4j-spring-boot4-starter`
  - `langchain4j-open-ai-spring-boot4-starter`
- `LangChainConfig` 使用了 `@ConditionalOnProperty(prefix = "lang-chain", name = "enabled", havingValue = "false")`，当配置 `lang-chain.enabled` 为 `false` 时加载。
- **内置测试主方法**：
  在 [`LangChainConfig.java`](file:///Users/ulpon/Documents/project/ulpon/ulpon-backend/ruoyi-modules/ruoyi-ai/src/main/java/org/dromara/ai/config/LangChainConfig.java) 中提供了一个直接运行测试的方法：
  ```java
  public static void main(String[] args) {
      OpenAiChatModel build = OpenAiChatModel.builder()
          .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1") // 阿里通义千问兼容接口
          .apiKey("sk-fdf746a2900b48c38da6c78794d9e692")              // API 密钥
          .modelName("qwen3.7-flash")                                // 大模型名称
          .build();
      String chat = build.chat("你好，你是谁");
      log.error(chat);
  }
  ```
  这表明底层的适配不仅支持 OpenAI 官方接口，还可以通过兼容的 v1 接口规范对接国内外各类大模型服务。

---

### 5. 核心接口与服务设计
1. **智能体操作定义**：
   - 接口 `IAiAgentService`：定义了系统内自主设计智能体的增删改查生命周期控制：
     - `info(Long agentId)`: 查询智能体详情。
     - `list()`: 获取智能体列表。
     - `create()`: 新增智能体。
     - `update()`: 修改智能体。
     - `delete()`: 删除智能体。
2. **实现层次结构**：
   - `AiAgentServiceImpl` 当前作为一个空业务类注入到 Spring 容器中，继承 `ruoyi-common-ai` 依赖引入的同名服务接口并提供覆写实现，留待后续结合业务数据库表或对接 Snail AI SDK 内的智能体管理模块进行具体业务逻辑填充。
   - `BaseAiChatService` 为基础 AI 对话的通用基类或服务，预留了各类 LLM 适配后的通用会话管理接口。
