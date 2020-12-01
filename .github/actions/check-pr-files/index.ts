import * as core from '@actions/core';
import * as github from '@actions/github';
import { GitHub } from '@actions/github/lib/utils';
import { PullsListFilesResponseData } from '@octokit/types';

function getInputAsList(label: string, opts?: core.InputOptions): string[] {
  return core.getInput(label, opts).split("\n").map(s => s.trim()).filter(x => x !== '');
}

async function getChangedFiles(client: InstanceType<typeof GitHub>, prNumber: number): Promise<string[]> {
  const listFilesOptions = client.pulls.listFiles.endpoint.merge({
    owner: github.context.repo.owner,
    repo: github.context.repo.repo,
    pull_number: prNumber
  });

  const listFilesResponse: PullsListFilesResponseData =
    await client.paginate(listFilesOptions);
  const changedFiles = listFilesResponse.map(v => v.filename);

  core.debug("found changed files:");
  for (const file of changedFiles) {
    core.debug("  " + file);
  }

  return changedFiles;
}

async function run() {
  try {
    const pr = github.context.payload.pull_request;
    if (!pr) {
      core.warning('Not a PR. Skipped.');
      return;
    }

    const prTitle: string = pr.title;
    if (prTitle.toLowerCase().includes('refine')) {
      core.info('Ignored a refine PR.');
      return;
    }

    core.setSecret('token');
    const token = core.getInput('token', {required: true});
    const octokit = github.getOctokit(token);
    const changedFiles = await getChangedFiles(octokit, pr.number);
    core.info(`PR included files: ${changedFiles}`);

    const files = getInputAsList('files', {required: true});
    core.info(`Checked files: ${files}`);

    // for (let file of files) {
    //   let included = false;
    //   core.info(`${file}: included: ${included}`);
    // }

    //octokit.issues.addLabels(); //TODO
  } catch (e) {
    core.setFailed(`Error: ${e}`);
  }
}

run();
