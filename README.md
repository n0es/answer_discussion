<div class="main">
  <h4 class="heading">
    <a name="(part._)"></a
    ><span class="AssignmentNum">Assignment 6-api:</span> api, generics,
    whole-system integration and design practice<span class="button-group"
      ><a href="#(part._)" class="heading-anchor" title="Link to here">🔗</a
      ><span style="visibility: hidden"> </span
    ></span>
  </h4>
  <p>
    <span style="font-weight: bold">Goals:</span> Practice working with an API,
    and generic types. Practice design and implementation on a real-world
    problem.
  </p>
  <h5 class="heading">
    <a name="(part._.Instructions)"></a>Instructions<span class="button-group"
      ><a
        href="#(part._.Instructions)"
        class="heading-anchor"
        title="Link to here"
        >🔗</a
      ><span style="visibility: hidden"> </span
    ></span>
  </h5>
  <p>As always, be very careful with your naming conventions.</p>
  <p>The submissions will be organized as follows:</p>
  <ul>
    <li>
      <p>
        <span style="font-weight: bold">Submission Homework 6 Problem 1:</span>
        The <span class="stt">Canvas</span> discussion post answerer.
      </p>
    </li>
  </ul>
  <p></p>
  <div class="SIntrapara">
    <span style="font-weight: bold">Due Dates:</span>
  </div>
  <div class="SIntrapara">
    <ul>
      <li>
        <p>
          Problem 1 – Code Sunday, November 17th at 10:00pm, Demo November 18th
          during class
        </p>
      </li>
    </ul>
  </div>
  <p></p>
  <h5 class="heading">
    1<tt>&nbsp;</tt
    ><a name="(part._.Problem_1_--_.Canvas_discussion_post_answerer)"></a
    >Problem 1 – <span class="stt">Canvas</span> discussion post answerer<span
      class="button-group"
      ><a
        href="#(part._.Problem_1_--_.Canvas_discussion_post_answerer)"
        class="heading-anchor"
        title="Link to here"
        >🔗</a
      ><span style="visibility: hidden"> </span
    ></span>
  </h5>
  <p>
    This project involves a synthesis of things we have studied and practiced,
    things we have not studied for you to explore and master on your own,
    integration with real-world systems and real-world documentation, and
    solving a truly extrinsically motivated problem.
  </p>
  <p>
    Your task for this assignment is to design and implement a command-line
    executable program that takes no arguments and that, when executed, will
    perform whatever task is asked in our course’s most recent Canvas discussion
    post, with respect to the most recent PDF document in our course’s
    <span class="stt">Files&gt;Documents</span> on Canvas.
  </p>
  <p>
    Your program will interact remotely with Canvas and will almost certainly
    use Microsoft’s Azure service.
  </p>
  <p>
    You should follow good design recipe practice. Have one purpose per method,
    one purpose per interface/class/unit of code. You should clearly articulate
    this purpose statement. I absolve you of needing to write your templates.
    Each method should be well tested.
  </p>
  <p>
    You should be prepared to demonstrate to me that you can invoke
    <span class="stt">./answer_discussion</span> (or
    <span class="stt">./answer_discussion.exe</span> on Windows for those who
    wish) to execute your program, and that alone should do everything
    necessary.
  </p>
  <h5 class="heading">
    1.1<tt>&nbsp;</tt><a name="(part._.Hints_)"></a>Hints:<span
      class="button-group"
      ><a href="#(part._.Hints_)" class="heading-anchor" title="Link to here"
        >🔗</a
      ><span style="visibility: hidden"> </span
    ></span>
  </h5>
  <ul>
    <li>
      <p>
        <span style="font-weight: bold"
          >Every resource is open and valid to use.</span
        >
        No one else has their students doing this project. Free to use example
        source code you find online (providing you follow applicable
        intellectual property laws), the LLM of your choice, textbooks, blogs,
        API documentation, 3rd party code or extensions, interactive video
        tutorials, etc. The only thing you can’t use is some other
        student/pair’s code or solution. You can talk to other students about
        ideas but it shouldn’t get to the level of looking at or describing your
        source code to them or vica versa. You also shouldn’t hire an outside
        consultant to do this for you.
      </p>
    </li>
    <li>
      <p>
        To help you with the above, recall that you have access to
        <a href="https://copilot.microsoft.com/" class="plainlink"
          >Enterprise instances of Microsoft Copilot</a
        >. You can also access Copilot from <span class="stt">bing.com</span> on
        Edge—<wbr />if you are logged out you can work around the 30 queries/day
        limitation.
      </p>
    </li>
    <li>
      <p>
        You will likely come across new aspects of Java like static methods: go
        figure them out.
      </p>
    </li>
    <li>
      <p>
        Microsoft offers heaps of
        <a href="https://learn.microsoft.com/en-us/" class="plainlink"
          >documentation, learning, and support</a
        >
        for their products. You should consider going through relevant tutorials
      </p>
    </li>
    <li>
      <p>
        You are welcome to continue using the
        <span class="stt">Tester.jar</span> library, but the more common
        industrial tool is
        <a href="https://junit.org/junit5/" class="plainlink">JUnit</a>.
        Consider <a href="https://jqwik.net/" class="plainlink">JQwik</a> for
        property-based tests.
      </p>
    </li>
    <li>
      <p>
        If existing command-line/scriptable tools already exist to solve parts
        of your problem, feel free to use them. This time I don’t
        <span style="font-style: italic">want</span> you to re-invent the wheel.
        Under WSL, you should use <span class="stt">apt-get</span>.
      </p>
    </li>
    <li>
      <p>
        Last time I checked, <span class="stt">chocolatey</span> was the
        best-in-breed software package manager for Windows. I don’t know what
        the state of the art is these days. You should figure that out and
        install your software that way.
      </p>
    </li>
    <li>
      <p>
        You will find some loosely related Python code at the following two
        repositories:
      </p>
      <ul>
        <li>
          <p>
            <a
              href="https://github.com/jasonhemann/canvas-api-project"
              class="plainlink"
              ><span class="stt">canvas-api-project</span></a
            >
          </p>
        </li>
        <li>
          <p>
            <a
              href="https://github.com/jasonhemann/query_tool"
              class="plainlink"
              ><span class="stt">query-tool</span></a
            >
          </p>
        </li>
      </ul>
      <p>
        Please note that I mean it when I say this is loosely related. You will
        still need to figure out a substantial number of things on your own.
      </p>
    </li>
    <li>
      <p>
        I do not care which shell you use; you can use whichever you find most
        convenient. I believe that for Windows users your choices are:
      </p>
      <ul>
        <li>
          <p><span class="stt">cmd.exe</span></p>
        </li>
        <li>
          <p><span class="stt">powershell</span></p>
        </li>
        <li>
          <p>
            <span class="stt">bash</span> (or <span class="stt">zsh</span>,
            <span class="stt">fish</span>, etc) under WSL
          </p>
        </li>
        <li>
          <p>
            <a
              href="https://www.pascallandau.com/blog/setting-up-git-bash-mingw-msys2-on-windows/"
              class="plainlink"
              >Some unholy frankenshell monstrosity you can jimmy together</a
            >
          </p>
        </li>
      </ul>
    </li>
    <li>
      <p>
        You can use your shell language or some scripting language of your
        choice to kick off your Java program, but the bulk of the programming
        should be done in Java. You are also welcome to do your script
        programming in Java, although I would not recommend it.
      </p>
    </li>
    <li>
      <p>
        Your command-line process can kick off a compile-execute sequence, or
        you can pre-build and deploy your executable, so that executing your
        program on the command line causes an executable jar to run.
      </p>
    </li>
    <li>
      <p>
        When you are testing, you should be testing against the
        <a
          href="https://setonhall.test.instructure.com/doc/api/live"
          class="plainlink"
          >test site</a
        >. Do not test in production!!
      </p>
    </li>
    <li>
      <p>
        As you are learning programmatic dependency management in Java, you
        should look into
        <a href="https://maven.apache.org/" class="plainlink">Maven</a> and/or
        <a href="https://gradle.org/" class="plainlink"></a>Gradle
      </p>
    </li>
  </ul>
  <h5 class="heading">
    1.2<tt>&nbsp;</tt><a name="(part._.Details_)"></a>Details:<span
      class="button-group"
      ><a href="#(part._.Details_)" class="heading-anchor" title="Link to here"
        >🔗</a
      ><span style="visibility: hidden"> </span
    ></span>
  </h5>
  <p>
    The only things I put in <span class="stt">Files&gt;Documents</span> will be
    machine-readable PDF documents. No other file formats will be used, and the
    only PDFs you need to consider are the ones posted in that location.
  </p>
  <p>
    You must use Microsoft’s Azure to access your LLM. I’m being deliberate here
    because I <span style="font-style: italic">want</span> you to have to do
    something slightly different from the most popular tutorials on the
    internet. You have
    <a href="https://portal.azure.com/" class="plainlink"
      >$100 free credit to use</a
    >.
  </p>
  <p>
    You must also ensure the quality of your code using appropriate language
    tooling and configurations for these should also be managed under version
    control. You should programmatically check for/ensure conformance to a
    coding style standard, and you should use some tooling to assess the quality
    of your code, tests, and test coverage.
  </p>
  <p>
    The instructions for your LLM to follow will be contained entirely in the
    body of the discussion prompt—<wbr />you can ignore the discussion post
    title.
  </p>
  <p>
    Your program should be robust enough to handle a folder with a large
    collection of documents, and still find the most recent.
  </p>
  <p>If it matters, determine "most recent" by the creation date.</p>
  <p>
    Your program should continue to work even after I change the discussion post
    or add a new document—<wbr />that is to say, you must not "hard code" in
    either the document or the instructions.
  </p>
  <p>
    Do not hard-code your API keys into your code. That would be a very poor
    practice. Instead, store the API key(s) in environment variables, and use
    system calls to collect that value.
  </p>
  <p>
    Have a robust test suite. What happens if there are no documents available?
    What if two documents were posted at the exact same time? What if the
    download of that document fails part-way through? What happens if the API
    key cannot be found? What happens if the API key is invalid? What happens if
    the file system runs out of disk space while you are writing the document?
    Will your program always be able to write to disk? What happens if your
    Azure account runs out of credits partway through? Write tests and check for
    such situations.
  </p>
  <p>
    Write a well-written <span class="stt">README.md</span> file at the top
    level of your repository. It should explain the prerequsites for using your
    software, how to set it up and configure it, the types and kinds of
    arguments, and any other options or features it provides. You would be
    surprised how difficult it can be to use software without an adequate
    README.
  </p>
  <h5 class="heading">
    1.3<tt>&nbsp;</tt><a name="(part._.Bonus_)"></a>Bonus:<span
      class="button-group"
      ><a href="#(part._.Bonus_)" class="heading-anchor" title="Link to here"
        >🔗</a
      ><span style="visibility: hidden"> </span
    ></span>
  </h5>
  <ul>
    <li>
      <p>
        <span style="font-weight: bold">"Soft scanned" PDFs</span> You can make
        your program more robust by using a model that accepts "soft-scanned"
        PDFs—<wbr />documents whose contents are not machine readable—<wbr />to
        extract the text contents of that PDF before solving the discussion
        post.
      </p>
    </li>
    <li>
      <p><span style="font-weight: bold">Audio recorded responses</span></p>
      <p>
        Some instructors are newly
        <a
          href="https://app.sparkmailapp.com/web-share/N6VeJdAAyzlbrfkAxyzkhxXhtt6_sJacxMcIgxWp"
          class="plainlink"
          >interested in audio recorded responses</a
        >. Make your program accept a command-line flag
        <span class="nobreak"><span class="stt">--audio</span></span
        >. When invoked with this flag, your program will create and upload an
        audio recording instead of posting raw plain text.
      </p>
      <p>
        Use a model that will use small clips of your voice and the text to be
        read, and produce an audio recording of your response that you then
        upload. It needs to be a real human-sounding voice, and should in fact
        sound like you. No credit for using an old-school like text-to-speech
        engine.
      </p>
    </li>
    <li>
      <p>
        <span style="font-weight: bold">CI/CD pipeline and commit hooks</span>
      </p>
      <p>
        Running all of your test suite locally could be expensive. One solution
        is to use your version control system’s continuous integration /
        continuous delivery pipeline to perform those checks for you.
      </p>
      <p>
        Create a runner for your CI platform of choice (we have campus-wide
        access to <a href="https://bitbucket.org/" class="plainlink"></a
        ><span class="stt">bitbucket</span> via your school Atlassian account.
        and its
        <a
          href="https://www.atlassian.com/software/bitbucket/features/pipelines"
          class="plainlink"
          >pipelines</a
        >
        for instance), or if you prefer to use
        <span class="stt">github.com</span> they also have
        <a
          href="https://github.com/resources/articles/devops/ci-cd"
          class="plainlink"
          >Github Actions</a
        >.
      </p>
    </li>
    <li>
      <p><span style="font-weight: bold">Docker</span></p>
      <p>
        As you have probably noticed, getting all the software installed,
        running and correctly configured is a chore. Having to manage a remote
        installation of all those tools in userspace on a target machine would
        be quite the chore! If you want, you can build and submit your whole
        program and all its dependencies as a Docker image that I can run on an
        86-64 machine (my laptop is an Apple Silicon machine, but I’ll be
        running it from within another container on a remote machine).
      </p>
      <p>
        Make sure you give me complete instructions on how to run and use it in
        your <span class="stt">README.md</span>.
      </p>
    </li>
  </ul>
  <div class="navsetbottom">
    <span class="navleft"
      ><div class="nosearchform"></div>
      &nbsp;&nbsp;<span class="tocsettoggle"
        >&nbsp;&nbsp;<a
          href="javascript:void(0);"
          title="show/hide table of contents"
          onclick="TocsetToggle();"
          >contents</a
        ></span
      ></span
    ><span class="navright"
      >&nbsp;&nbsp;<a
        href="assignment5.html"
        title='backward to "Assignment 5: The Aliens Attack Again"'
        data-pltdoc="x"
        rel="prev"
        >← prev</a
      >&nbsp;&nbsp;<a
        href="Assignments.html"
        title='up to "Assignments"'
        data-pltdoc="x"
        >up</a
      >&nbsp;&nbsp;<a
        href="Pair_Programming_Overview.html"
        title='forward to "Pair Programming Overview"'
        data-pltdoc="x"
        rel="next"
        >next →</a
      ></span
    >&nbsp;
  </div>
</div>

## Docker Usage

To build and run the application using Docker:

1. Clone the repository
2. Copy `.env.example` to `.env` and fill in your credentials
3. Build the Docker image:

`docker build -t answer-discussion .`

4. Run the Docker image:

`docker run --env-file .env answer-discussion`